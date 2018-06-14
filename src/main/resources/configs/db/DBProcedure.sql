use train_db;
DELIMITER $
################################################################################
#记录存储过程错误日志的存储过程
###############################################################################
create table if not exists ErrLog
(
	id bigint primary key auto_increment
	,procedureName varchar(128) not null
	,sql_err_state varchar(16) 
	,msg varchar(1024)
	,errTime datetime not null
)engine=innodb auto_increment=1
$
drop procedure if exists logErr$
create procedure logErr(in procedure_name varchar(128),in sql_state varchar(16),in message varchar(1024))
begin
	declare tryTimes int;
	declare successFlag int;
	declare continue handler for sqlexception 
	begin
		rollback;
		set @tryTimes=@tryTimes+1;
	end
	;
	
	set @successFlag=0;
	set @tryTimes=0;
	set autocommit=0;
	while @tryTimes<=3 and @successFlag=0
	do
		start transaction;
		insert into ErrLog(procedureName,sql_err_state,msg,errTime)
		values
		(procedure_name,sql_state,message,now())
		;
		commit;
		set @successFlag=1;
	end while
	;
	set autocommit=1;
end
$

################################################################################
#生成bill的存储过程
###############################################################################
drop procedure if exists generateBill$
create procedure generateBill(in execTime dateTime,out flag int)
begin
	declare logRangeStart datetime;
    declare logRangeEnd datetime;
    declare globalLastPayDate date;
    declare globalTotalDays int;
    
    declare exit handler for sqlexception
	begin
		get diagnostics condition 1 @sql_state=RETURNED_SQLSTATE,@msg=MESSAGE_TEXT;
		call  logErr('generateBill',@sql_state,@msg);
        set flag=1;
        rollback;
        set autocommit=1;
	end
	;
    #执行日的前三个月的第一天
    set @logRangeStart=(select date(date_sub(tmp,interval 1-day(tmp) day)) from (select date_sub(execTime,interval 3 month) tmp) init);
    #计算用户最后交款日期
    set @logRangeEnd=execTime;
    #执行日的当天
    set @globalLastPayDate=(select date(date_sub(tmp,interval 1-day(tmp)+1 day)) from (select date_add(execTime,interval 1 month) tmp)init);
    #该季度账单总天数
    set @globalTotalDays=datediff(@logRangeEnd,@logRangeStart);
    
	set transaction isolation level SERIALIZABLE;
	#更新Account的StateLog
	create temporary table if not exists tmpStateUpdateInfo
	(
        accountId bigint primary key
        ,oldStateLogId bigint 
        ,newStateLogId bigint
	)engine=myisam
	;
    truncate table tmpStateUpdateInfo;
	#创建临时表记录bill和AccountStateLog对应关系
    create temporary table if not exists tmpBillStateLogMap
    (
		accountId bigint
        ,newBillId bigint
        ,stateLogId bigint
        ,Days int
    )engine=myisam
    ;
    truncate table tmpBillStateLogMap;
    
	set autocommit=0;
    start transaction;
    ##############################################################################
    ##对出bill时为正常状态的Account生成bill
    ##############################################################################
    #筛选状态是正常状态的Account，生成与之对应的新的stateLogId
    insert into tmpStateUpdateInfo
    select 
        a.id
        ,a.currentStateLogId
        ,@tmpStateLogId:=@tmpStateLogId+1
	from 
		(select id,currentStateLogId
        from Account
		where state=0
        )a
        ,(select 
			@tmpStateLogId:=
				ifnull((select max(id) from AccountStateLog),1)
		)init
	;
    #更新StateLog中的旧state结束时间，把Account中更新成新的stateLogId
    update 
		tmpStateUpdateInfo a
        inner join
        AccountStateLog b
		on a.oldStateLogId=b.id
        inner join
        Account c
        on a.accountId=c.id
	set
		b.endTime=execTime
        ,c.currentStateLogId=a.newStateLogId
    ;
    #添加新的stateLog进表中
    insert into AccountStateLog(id,accountId,state,startTime)
    select
		newStateLogId
        ,accountId
        ,0
        ,execTime
	from tmpStateUpdateInfo
    ;
    ##生成账单
    #每个bill对应的stateLog条件是：时间范围是季度开始到执行时间之前，且stateLog的state是正常，且billId为null
    insert into tmpBillStateLogMap
    select 
        a.accountId
        ,a.newBillId
        ,b.id 
        ,datediff(endTime,startTime) Days
	from 
	(select 
		@tmpNewBillId:=@tmpNewBillId+1 newBillId
        ,acc.id accountId
	from 
		#利用之前的临时表筛选Account
		tmpStateUpdateInfo tmp
        inner join
        Account acc
        on acc.id=tmp.accountId
        ,(select @tmpNewBillId:=ifnull((select max(id) from Bill for update),1)
		)init
    )a
    inner join
    #筛选出新生成bill对应的stateLog
    (select 
		id
        ,accountId
        ,endTime,startTime
	from AccountStateLog
    where state=0
      and startTime >= @logRangeStart
      and endTime <= @logRangeEnd
      and billId is null
    )b
    on a.accountId=b.accountId
    ;
    # 计算账单价格插入账单表
	insert into Bill(id,userId,parkingLotId,accountId,price,lastPayDate)
	select 
		a.newBillId
		,b.userid as userId
		,b.parkingLotId as parkingLotId
		,a.accountId as accountId
		,cast(b.price*3 * totalDays/@globalTotalDays as decimal(10,4))
		as billPrice
		,@globalLastPayDate
	from 
		(select accountId,newBillId,sum(Days) totalDays
        from tmpBillStateLogMap 
        group by 1,2
        )a
        inner join
		Account b
		on a.accountId=b.id
	;
	# 更新currentBillMap表,即更新该账号当前需要支付的账单
	insert into CurrentBillMap(accountId,currentBillId)
	select
		accountId
		,newBillId
	from tmpBillStateLogMap
	;
    # 更新AccountStateLog中的billId
    update 
		AccountStateLog a
        inner join
        tmpBillStateLogMap b
		on a.id=b.stateLogId
	set a.billId=b.newBillId
    ;
    ##############################################################################
    ##对出bill时为非常状态的Account生成bill
    ##############################################################################
    ##生成账单
    #筛选欠费状态的用户
    #每个bill对应的stateLog条件是：时间范围是季度开始到执行时间之前，且stateLog的state是正常，且billId为null
    delete from tmpBillStateLogMap;
    insert into tmpBillStateLogMap
    select 
        a.accountId
        ,a.newBillId
        ,b.id 
        ,datediff(endTime,startTime) Days
	from 
	(select 
		@tmpNewBillId:=@tmpNewBillId+1 newBillId
        ,acc.id accountId
	from 
		(select *
		from Account
		where state=-1
		)acc
        ,(select @tmpNewBillId:=ifnull((select max(id) from Bill for update),1)
		)init
    )a
    inner join
    #筛选出新生成bill对应的stateLog
    (select 
		id
        ,accountId
        ,endTime,startTime
	from AccountStateLog
    where state=0
      and startTime >= @logRangeStart
      and endTime <= @logRangeEnd
      and billId is null
    )b
    on a.accountId=b.accountId
    ;
    # 计算账单价格插入账单表
	insert into Bill(id,userId,parkingLotId,accountId,price,lastPayDate)
	select 
		a.newBillId
		,b.userid as userId
		,b.parkingLotId as parkingLotId
		,a.accountId as accountId
		,cast(b.price*3 * totalDays/@globalTotalDays as decimal(10,4))
		as billPrice
		,null
	from 
		(select accountId,newBillId,sum(Days) totalDays
        from tmpBillStateLogMap 
        group by 1,2
        )a
        inner join
		Account b
		on a.accountId=b.id
	;
	# 更新currentBillMap表,即更新该账号当前需要支付的账单
	insert into CurrentBillMap(accountId,currentBillId)
	select
		accountId
		,newBillId
	from tmpBillStateLogMap
	;

    # 更新AccountStateLog中的billId
    update 
		AccountStateLog a
        inner join
        tmpBillStateLogMap b
		on a.id=b.stateLogId
	set a.billId=b.newBillId
    ;
    
    
    # 同时更新最新的ParkingLot的价格到Account上
	update 
		Account a 
		inner join
		ParkingLot b
		on a.parkingLotId=b.id
	set 
		a.price=b.currentPrice
	;
	commit;
    set flag=0;
    set autocommit=1;

end
$
################################################################################
#系统自动停卡的存储过程
###############################################################################
drop procedure if exists updateAllAccountState$
create procedure updateAllAccountState(in execTime dateTime,out flag int)
begin
	declare step int;
	declare exit handler for sqlexception
	begin
		get diagnostics condition 1 @sql_state=RETURNED_SQLSTATE,@msg=MESSAGE_TEXT,@table_name=TABLE_NAME;
        call logErr('updateAllAccountState',@sql_state,@msg);
		rollback;
		set autocommit=1;
		set flag=1;
	end
	;
    set transaction isolation level SERIALIZABLE;
    
    # 通过临时表记录所有需要更新的Account的新旧日志id，用于更新旧日志结束时间和关联新日志id
	create temporary table if not exists tmpUpdateInfo
	(
		billId bigint
		,accountId bigint
		,curAccStateLogId bigint
		,newAccStateLogId bigint
	)engine=myisam
	;
    truncate TABLE tmpUpdateInfo;
    
	start transaction;
	set autocommit=0;
    #筛选出所有正常状态中Bill还未支付的账号
	#未支付账单定义为：currentBillMap中lastPayDate小于execTime的账单
    insert into tmpUpdateInfo (billId,accountId,curAccStateLogId,newAccStateLogId)
	select 
		a.id
		,b.id
		,b.currentStateLogId
		,@tmpNewId:=@tmpNewId+1
	from 
		(select bill.id id,curBill.accountId accountId
		from 
			(select * 
			from CurrentBillMap
			)curBill
			inner join
			(select * 
			from Bill
			where lastPayDate<execTime
			  and isPaid=0
			)bill
			on curBill.currentBillId=bill.id
		)a
		inner join
		(select id,currentStateLogId
		from Account 
		where state=0
		)b
		on a.accountId=b.id
		inner join
		(select @tmpNewId:=ifnull((select max(id) from AccountStateLog for update),1)
		)init
	;	
	
	#找到关联的Account并更新状态同时更新到stateLog中，并且从停车位中移除该账号，并且更新停车记录
    update 
		tmpUpdateInfo a 
		inner join
		Account b
		on a.accountId=b.id
		inner join
		ParkingPosition c
		on b.parkingPositionId=c.id
		left join
		ParkingRecord d
		on b.currentParkingRecId=d.id
        inner join
        AccountStateLog e
        on b.currentStateLogId=e.id
	set 
		b.parkingPositionId=null
		,b.state=-1
		,b.isParking=0
		,b.currentStateLogId=a.newAccStateLogId
		,c.accountId=null
		,d.endTime= execTime
        ,e.endTime= execTime
	;
	#在AccountStateLog记录Account的变化
	insert into AccountStateLog (id,accountId,state,startTime) 
	select
		newAccStateLogId
		,accountId
        ,-1
		,execTime
	from tmpUpdateInfo
	;
	
	set flag=0;
	commit;
    set autocommit=1;   
end
$

################################################################################
#支付账单的存储过程
###############################################################################
drop procedure if exists payBill$
create procedure payBill(in bill_id bigint,out flag int)
begin
	declare step int;
	declare exit handler for sqlexception
	begin
		get diagnostics condition 1 @sql_state=RETURNED_SQLSTATE,@msg=MESSAGE_TEXT,@table_name=TABLE_NAME;
		rollback;
		set autocommit=1;
		call logErr('payBill',@sql_state,@msg);
		set flag=1;
	end
	;
	
	set autocommit=0;
	start transaction;
	delete from CurrentBillMap where currentBillId=bill_id;
	update Bill
	set isPaid=1
	where id=bill_id
	;
	commit;
	set flag=0;
	set autocommit=1;
end
$


################################################################################
#开卡的存储过程
###############################################################################
# flag=0 事务正常完成
# flag=1 用户有其他卡为非正常状态
# flag=2 没有空停车位
# flag=4 停车卡id重复
# flag=8 系统执行错误
drop procedure if exists addNewCard$
create procedure addNewCard(in cardId bigint,in user_id bigint,in lot_id int,out accountId bigint,out flag int)
BEGIN
	declare positionId bigint;
	declare tmpAccountId bigint;
	declare tmpAccountStateLogId bigint;
	#定义检查变量
	declare checkUser bit;
	declare checkParkingPosition bit;
    declare exit handler for sqlexception
	begin
		get diagnostics condition 1 @sql_state=RETURNED_SQLSTATE,@msg=MESSAGE_TEXT;
        rollback;
        set autocommit=1;
		call logErr('addNewCard',@sql_state,@msg);
		#判断事务完成状态
		# flag=0 事务正常完成
		# flag=1 用户有其他卡为非正常状态
		# flag=2 没有空停车位
		# flag=4 系统执行错误
	    if @sql_state=23000 then
	    	set flag=(1<<2)|(@checkUser<<1)|(@checkParkingPosition);
	    else
	    	set flag=(1<<3)|(@checkUser<<1)|(@checkParkingPosition);
	    end if
		;
	    set accountId=@tmpAccountId
	    ;
	end
	;
    
	set @checkUser=1;
	set @checkParkingPosition=1;
	set autocommit=0;
	#开始更新操作
	start transaction;
	#添加相应的账号
	insert into Account(id,userId,parkingLotId,parkingPositionId,cardId,price)
	select
		@tmpAccountId:=
			ifnull((select max(id)+1 from Account for update),1)
		,a.tmp_user_id
		,lot_id
		,@positionId:=b.id parkingPositionId
		,cardId
		,currentPrice
	from 
		#检查对应parkingLot和User下没有没有不能使用的账号
		(select tmp_user_id,@checkUser:=0
		from (select user_id as tmp_user_id) tmpUserId 
		where
			(select count(1) from Account
			where userId = user_id
			  and parkingLotId = lot_id
			  and state=-1
			for update
			)=0
		)a
		inner join
		#检查停车场是否有空停车位,并锁定该行
		(select id ,@checkParkingPosition:=0
		from ParkingPosition
		where parkingLotId=lot_id
		  and state=0
		limit 1
		for update
		)b
		inner join
		(select currentPrice
		from ParkingLot where id=lot_id
		)c
	;
	#在AccountStateLog添加新账号的StateLog
	set @tmpAccountStateLogId:=
			ifnull((select max(id)+1 from AccountStateLog for update)
				,1)
	;
	insert into AccountStateLog (id,accountId,state,startTime)
	values
    (
		@tmpAccountStateLogId
		,@tmpAccountId
		,0
		,now()
	)
	;
	#更新Account表中的currentStateLogId字段
	update Account
	set currentStateLogId=@tmpAccountStateLogId
	where id=@tmpAccountId
	;
	#注册相应停车位
	update ParkingPosition
	set 
		accountId=@tmpAccountId
	where id=@positionId
	;
	#判断事务完成状态
	# flag=0 事务正常完成
	# flag=1 用户有其他卡为非正常状态
	# flag=2 没有空停车位
    set flag=(@checkUser<<1)|(@checkParkingPosition)
	;
	if flag>0 
	then rollback;
	else commit;
	end if;
	set autocommit=1;
    set accountId=@tmpAccountId;
END 
$
################################################################################
#停卡
###############################################################################
drop procedure if exists stopCard$
create procedure stopCard(in card_id bigint,out flag int)
begin
	declare newAccountStateLogId bigint;
	declare tmpAccountId bigint;
	declare tmpPositionId bigint;
	declare tmpUserId bigint;
	declare curLogId bigint;
	declare newBillId bigint;
	declare updateTime datetime; 
	#定义检查变量
    declare exit handler for sqlexception
	begin
		get diagnostics condition 1 @sql_state=RETURNED_SQLSTATE,@msg=MESSAGE_TEXT;
        rollback;
        set autocommit=1;
		call logErr('stopCard',@sql_state,@msg);
		set flag=1;
	end
	;

	set autocommit=0;
	start transaction;
	#初始化变量
	set @updateTime=
	(select now()
	from
		(select 
			@tmpAccountId:=id
			,@tmpUserId:=userId
			,@tmpPositionId:=parkingPositionId
			,@newAccountStateLogId:=(select max(id)+1 from AccountStateLog for update) newLogId
			,@curLogId:=currentStateLogId curLogId
		from Account
		where cardId=card_id
		for update
		)init
	)
	;
	#设置旧账单为已支付
	update Bill
	set isPaid=1
	where accountId=@tmpAccountId;
	delete from CurrentBillMap where accountId=@tmpAccountId;
	#生成新账单
	#对stateLog中没有billId且startTime在执行时间之前的生成对应bill
	insert into Bill(id,userId,parkingLotId,accountId,price,lastPayDate,isPaid)
	select 
		@newBillId:=ifnull((select max(id)+1 from Bill for update),1) id
		,b.userId as userId
		,b.parkingLotId as parkingLotId
		,a.accountId as accountId
		,cast(b.price * 3 * totalDays/getSeasonTotalDays(@updateTime) as decimal(10,4))
		as billPrice
		,null
		,1
	from 
		(select accountId,sum(datediff(end_time,start_time) + 1) totalDays
        from
        	(select accountId
        		,startTime start_time
        		,case
        			when endTime is not null then endTime
        			else @updateTime
        		end end_time
        	from AccountStateLog 
	        where accountId=@tmpAccountId
	          and state=0
	          and startTime<=@updateTime
	        )tmp
	    group by 1
        )a
        inner join
		Account b
		on a.accountId=b.id
	;
	#把新生成的账单更新到对应的stateLog上
	update AccountStateLog
	set billId=@newBillId
	where accountId=@tmpAccountId
      and state=0
      and startTime<=@updateTime
	;
	#更新stateLog
	update AccountStateLog 
	set
		endTime=@updateTime	
	where id=@curLogId
	;
	insert into AccountStateLog (id,accountId,state,startTime)
	values
    (
		@newAccountStateLogId
		,@tmpAccountId
		,-2
		,@updateTime
	)
	;
	
	#注销停车位
	update ParkingPosition
	set
		state=0
		,accountId=null
	where id=@tmpPositionId
	;
	#更新账号
	update Account
	set
		currentStateLogId=@newAccountStateLogId
		,parkingPositionId=null
		,state=-2
		,cardId=null
	where cardId=card_id
	;
	commit;
	set autocommit=1;
	set flag=0;
end
$


################################################################################
#支付账单后继续使用的存储过程
###############################################################################
drop procedure if exists resumeCard$
create procedure resumeCard(in position_id bigint,in account_id bigint,out flag int)
begin
	declare newAccountStateLogId bigint;
	declare curLogId bigint;
	declare updateTime datetime; 
	#定义检查变量
    declare exit handler for sqlexception
	begin
		get diagnostics condition 1 @sql_state=RETURNED_SQLSTATE,@msg=MESSAGE_TEXT;
        rollback;
        set autocommit=1;
		call logErr('resumeCard',@sql_state,@msg);
		set flag=1;
	end
	;
	
	set autocommit=0;
	start transaction;
	
	set @updateTime=
	(select now()
	from
		(select 
			@newAccountStateLogId:=(select max(id)+1 from AccountStateLog for update) newLogId
			,@curLogId:=currentStateLogId curLogId
		from Account
		where id=account_id
		for update
		)init
	)
	;
	#更新账单状态
	update 
		(select currentBillId
		from CurrentBillMap
		where accountId=account_id
		)a
		inner join
		Bill b
		on a.currentBillId=b.id
	set
		isPaid=1
	;
	delete from CurrentBillMap where accountId=account_id;
	
	#更新stateLog
	update AccountStateLog 
	set
		endTime=@updateTime	
	where id=@curLogId
	;
	insert into AccountStateLog (id,accountId,state,startTime)
	values
    (
		@newAccountStateLogId
		,account_id
		,0
		,@updateTime
	)
	;
	
	#注册停车位
	update ParkingPosition
	set
		state=1
		,accountId=account_id
	where id=position_id
	;
	#更新账号
	update Account
	set
		currentStateLogId=@newAccountStateLogId
		,parkingPositionId=position_id
		,state=0
	where id=account_id
	;
	commit;
	set autocommit=1;
	set flag=0;
end
$
	

################################################################################
#停车和提车相关的存储过程
###############################################################################
drop procedure if exists parkCar$
create procedure parkCar(in accountId bigint,out flag int)
begin
	declare parkingRecordId bigint;
	declare iscurParking int;
	declare exit handler for sqlexception 
	begin
		get diagnostics condition 1 @sql_state=RETURNED_SQLSTATE,@msg=MESSAGE_TEXT;
        call logErr('parkCar',@sql_state,@msg);
		rollback;
		set autocommit=1;
		set flag=(1<<1)|iscurParking;
	end
	;
	set flag=0;
	set autocommit=0;
	
	start transaction;
	set iscurParking:=
		(select isParking from Account where id=accountId for update);
	if iscurParking = 0 then
		begin
			set @parkingRecordId:=
					ifnull((select max(id)+1 from ParkingRecord for update),1)
			;
			update Account
				set currentParkingRecId = @parkingRecordId
					,isParking = 1
			where id=accountId
			;
			insert into ParkingRecord (id,lotId,positionId,accountId,startTime)
			select
				@parkingRecordId
				,parkingLotId lotId
				,parkingPositionId positionId
				,accountId 
				,now() startTime
			from 
				(select * from Account
				where id=accountId
				for update) tmp
			;
		end;
    end if
	;
	commit;
	set autocommit=1;
	set flag=iscurParking;
end 
$

drop procedure if exists pickCar$
create procedure pickCar(in accountId bigint,out flag int)
begin
	declare exit handler for sqlexception 
	begin
		get diagnostics condition 1 @sql_state=RETURNED_SQLSTATE,@msg=MESSAGE_TEXT;
        call logErr('pickCar',@sql_state,@msg);
		rollback;
		set autocommit=1;
		set flag=1;
	end
	;
	set autocommit=0;
	start transaction;
	update ParkingRecord
		set endTime = now()
	where id=(select currentParkingRecId from Account where id=accountId for update)
	;
	update Account
		set currentParkingRecId = null
			,isParking=0
	where id=accountId
	;
	commit;
	set autocommit=1;
	set flag=0;
end 
$

/*
--drop procedure if exists autoStopCard$
--create procedure autoStopCard()
--begin 
--	start transaction;
--	 update Account 
--	    set state=-2
--	 where id=(
--	 select accountId from Bill where isPaid=-1 for update 
--	 );
--	 commit;
--end 
--$
*/
DELIMITER ;

