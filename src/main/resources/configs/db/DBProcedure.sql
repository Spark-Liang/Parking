use train_db;
#生成bill的储存过程
DELIMITER $
drop procedure if exists generateBill$
create procedure generateBill(in billDate date)
begin
	start transaction;
	set transaction isolation level SERIALIZABLE;
	# 对非stop的账户生成当前bill的开始时间和结束时间
	insert into Bill(userId,parkingLotId,accountId,price,billStartDate,billEndDate)
	select 
		a.userid as userId
		,a.parkingLotId as parkingLotId
		,a.id as accountId
		,b.currentPrice * getCardUsedDays(a.stateStartDate,billDate)/getTotalDate(billDate)
		as price
		,a.stateStartDate as billStartDate
		,date_sub(billDate,INTERVAL 1 DAY) as billEndDate
		
	from 
		(select *
		from Account
		where state<>-1
		)as a
		inner join
		ParkingLot b
		on a.parkingLotId=b.id
	;
	
	# 更新Account表中,Bill表中最新的bill即是account新的currentBill
	update 
		Account a 
		inner join
		(select 
			a.*
			,@rank:=
			case
				when @last_account=accountId
				then @rank+1
				else 1
			end rank
			,@last_account:=accountId as last_account
		from 
			(select * from Bill
			order by accountId,billStartDate desc
			)a
			,(select @rank:=0,@last_account:=0) init
		having rank=1
		)bill
		on a.id=bill.accountId
	set
		a.currentBillId=bill.id
	where a.state<>-1
	;
	commit;
end 
$

drop procedure if exists updateAllAccountState$
create procedure updateAllAccountState(in execDate date,out flag int)
begin
	declare continue handler for sqlexception set flag=1;
	start transaction;
	set transaction isolation level SERIALIZABLE;
	# 通过临时表记录所有需要更新的内容
	/*create temporary table tmpUpdateInfo
	(
		billId bigint
		,
		,
	)type= heap*/
	
	#找到所有lastPayDate在execDate之前的未支付的账单
	#找到关联的Account并更新状态，并且从停车位中移除该账号，并且更新停车记录
	update 
		(select id
		from Bill
		where isPaid = 0 and lastPayDate < execDate
		)a 
		inner join
		Account b
		on a.id=b.currentBillId
		inner join
		ParkingPosition c
		on b.parkingPositionId=c.id
		left join
		ParkingRecord d
		on b.currentParkingRecId=d.id
	set 
		b.parkingPositionId=null
		,b.state=-1
		,b.isParking=0
		,c.accountId=null
		,d.endTime=now()
	;
	#在AccountStateLog记录Account的变化
	insert into AccountStateLog (accountId,state,startTime) 
	select
		a.id
		,-1
		,now()
	from 
		(select id
		from Bill
		where isPaid = 0 and lastPayDate < execDate
		)a 
		inner join
		Account b
		on a.id=b.currentBillId
	;
	#从停车位表中移除对应的Account
	update 
		ParkingPosition a
		inner join
		Account b
		on a.id=b.parkingPositionId
	set 
		a.accountId=null
	where b.state=-1
	;
	
	commit;
end
$

drop procedure if exists addNewCard$
create procedure addNewCard(in cardId bigint,in userId bigint,in lotId int,out accountId bigint,out flag int)
begin
	declare positionId bigint;
    declare tmpAccountId bigint;
    declare tmpAccountStateLogId bigint;
	#定义检查变量
	declare checkUser bit;
	declare checkParkingPosition bit;
	set @checkUser=1;
	set @checkParkingPosition=1;
	#开始更新操作
	start transaction;
    /*
    select userId,@checkUser:=0 checkUser
		from (select userId as userId) tmpUserId 
		where
			(select count(1) from Account
			where userId = userId
			  and parkingLotId = lotId
			  and state=-1
			for update
			)=0 
	;
    select id ,@checkParkingPosition:=0 checkPosition
		from ParkingPosition
		where parkingLotId=lotId
		  and state=0
		limit 1
	;
	select
		@tmpAccountId:=(select id+1 from Account 
				where id>=(select max(id) from Account)
				order by id desc
				limit 1
				for update) id
		,a.userId userId
		,lotId parkingLotId
		,@positionId:=b.id parkingPositionId
		,cardId cardId
		,curdate() stateStartDate
	from 
		#检查对应parkingLot和User下没有没有不能使用的账号
		(select userId,@checkUser:=0
		from (select userId as userId) tmpUserId 
		where
			(select count(1) from Account
			where userId = userId
			  and parkingLotId = lotId
			  and state=-1
			for update
			)=0 
		)a
		inner join
		#检查停车场是否有空停车位
		(select id ,@checkParkingPosition:=0
		from ParkingPosition
		where parkingLotId=lotId
		  and state=0
		limit 1
		for update
		)b
	;*/
	#添加相应的账号
	insert into Account(id,userId,parkingLotId,parkingPositionId,cardId,price)
	select
		@tmpAccountId:=(select id+1 from Account 
				where id>=(select max(id) from Account)
				order by id desc
				limit 1
				for update) id
		,a.userId
		,lotId
		,@positionId:=b.id parkingPositionId
		,cardId
		,currentPrice
	from 
		#检查对应parkingLot和User下没有没有不能使用的账号
		(select userId,@checkUser:=0
		from (select userId as userId) tmpUserId 
		where
			(select count(1) from Account
			where userId = userId
			  and parkingLotId = lotId
			  and state=-1
			for update
			)=0 
		)a
		inner join
		#检查停车场是否有空停车位,并锁定该行
		(select id ,@checkParkingPosition:=0
		from ParkingPosition
		where parkingLotId=lotId
		  and state=0
		limit 1
		for update
		)b
		inner join
		(select currentPrice
		from ParkingLot where id=lotId
		)c
	;
    /*
    select @tmpAccountId accountId,@positionId positionId;
    select id,userId,parkingLotId,parkingPositionId,cardId,stateStartDate
    from Account 
    where id=@tmpAccountId;
    */
	#在AccountStateLog添加新账号的StateLog
	insert into AccountStateLog (id,accountId,state,startTime)
	select
		@tmpAccountStateLogId:=
			case 
				when 
					(select @tmpAccountStateLogId:=id+1 from AccountStateLog
					where id>=(select max(id) from AccountStateLog)
					order by id desc
					limit 1 for update
					) is not null then @tmpAccountStateLogId
				else 1
			end id
		,@tmpAccountId
		,0
		,now()
	;
	#更新Account表中的currentStateLogId字段
	update Account
	set currentStateLogId=@tmpAccountStateLogId
	where id=@tmpAccountId
	;
	update ParkingPosition
	set 
		accountId=@tmpAccountId
		,state=1
	where id=@positionId
	;
	commit;
	
	#判断事务完成状态
	# flag=0 事务正常完成
	# flag=1 用户有其他卡为非正常状态
	# flag=2 没有空停车位
    /*
    select @checkUser checkUser,@checkParkingPosition checkParkingPosition,(@checkUser<<1)|(@checkParkingPosition) flag ;
	*/
    set flag=(@checkUser<<1)|(@checkParkingPosition)
	;
    set accountId=@tmpAccountId
    ;
end 
$

drop procedure if exists parkCar$
create procedure parkCar(in accountId bigint,out flag int)
begin
	declare parkingRecordId bigint;
	declare continue handler for sqlexception set flag=1;
	set flag=0;
	start transaction;
	insert into ParkingRecord (id,lotId,positionId,accountId,startTime)
	select
		@parkingRecordId:=
			case 
				when 
					(select @parkingRecordId:=id+1 from ParkingRecord
					where id>=(select max(id) from ParkingRecord)
					order by id desc
					limit 1 for update
					) is not null then @parkingRecordId
				else 1
			end id
		,parkingLotId lotId
		,parkingPositionId positionId
		,accountId 
		,now() startTime
	from 
		(select * from Account
		where id=accountId
		for update) tmp
	;
	update Account
		set currentParkingRecId = @parkingRecordId
			,isParking = 1
	where id=accountId
	;
	if (flag > 0) then
		rollback;
	else
		commit;
	end if
	;
end 
$

drop procedure if exists pickCar$
create procedure pickCar(in accountId bigint,out flag int)
begin
	declare continue handler for sqlexception set flag=1;
	set flag=0;
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
	if (flag > 0) then
		rollback;
	else
		commit;
	end if
	;
end 
$
DELIMITER ;