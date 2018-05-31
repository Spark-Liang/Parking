
#生成bill的储存过程
DELIMITER $
drop procedure if exists generateBill$
create procedure generateBill(in billDate date)
begin
	start trainsaction;
	#对非stop的账户生成当前bill的开始时间和结束时间
	insert into Bill(userId,parkingLotId,accountId,price,billStartDate,billEndDate)
	select 
		a.userid as userId
		,a.parkingLotId as parkingLotId
		,a.id as accountId
		,b.currentPrice * datediff(billDate,a.stateStartDate)/getTotalDate(billDate)
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
	
	#更新Account表中,Bill表中最新的bill即是account新的currentBill
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
create procedure updateAllAccountState(in execDate date)
begin
	
	#更新所有Account的状态，未支付账单的状态设为s，其他不变
	update 
		Account a 
		inner join
		Bill b
		on a.currentBillId=b.id
	set 
		a.parkingPositionId=null
		,a.state=-1
	 	,a.stateStartDate=execDate
	where b.isPaid=0
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
end
$

drop procedure if exists addNewCard$
create procedure addNewCard(in cardId bigint,in userId bigint,in parkingLotId int,out accountId bigint,out flag int)
begin
	declare parkingPositionId bigint;
	#定义检查变量
	declare checkUser bit;
	declare checkParkingPosition bit;
	set checkUser=1;
	set checkParkingPosition=1;
	#开始更新操作
	start transaction;
	select
		@accountId:=(select id+1 from Account 
					where id>=(select max(id) from Account)
					order by id desc
					limit 1
					for update) id
		,a.userId
		,parkingLotId
		#,@parkingPositionId:=b.id parkingPositionId
		,b.id parkingPositionId
		,cardId
		,curdate()
	from 
		#检查对应parkingLot和User下没有没有不能使用的账号
		(select userId,@checkUser:=0
		from (select userId as userId) tmpUserId 
		where
			(select count(1) from Account
			where userId = userId
			  and parkingLotId = parkingLotId
			  and state=-1
			for update
			)=0 
		)a
		inner join
		#检查停车场是否有空停车位
		(select id ,@checkParkingPosition:=0
		from ParkingPosition
		where parkingLotId=parkingLotId
		  and state=0
		limit 1
		for update
		)b
	;
	insert into Account(id,userId,parkingLotId,parkingPositionId,cardId,stateStartDate)
	select
		@accountId:=(select id+1 from Account 
					where id>=(select max(id) from Account)
					order by id desc
					limit 1
					for update) id
		,a.userId
		,parkingLotId
		#,@parkingPositionId:=b.id parkingPositionId
		,b.id
		,cardId
		,curdate()
	from 
		#检查对应parkingLot和User下没有没有不能使用的账号
		(select userId,@checkUser:=0
		from (select userId as userId) tmpUserId 
		where
			(select count(1) from Account
			where userId = userId
			  and parkingLotId = parkingLotId
			  and state=-1
			for update
			)=0 
		)a
		inner join
		#检查停车场是否有空停车位
		(select id ,@checkParkingPosition:=0
		from ParkingPosition
		where parkingLotId=parkingLotId
		  and state=0
		limit 1
		for update
		)b
	;
	select id,userId,parkingLotId,parkingPositionId,cardId,stateStartDate
	from Account where id=@accountId
	;
	update ParkingPosition
	set 
		accountId=@accountId
		,state=1
	#where id=@parkingPositionId
	where id=(select parkingPositionId from Account where id=@accountId)
	;
	commit;
	#test 
	select @accountId as accountId,@parkingPositionId as parkingPositionId, @flag as flag;
	#判断事务完成状态
	# flag=0 事务正常完成
	# flag=1 用户有其他卡为非正常状态
	# flag=2 没有空停车位
	set flag=(@checkUser<<1)|(@checkParkingPosition)
	;
end 
$
DELIMITER ;