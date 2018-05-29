
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
DELIMITER ;