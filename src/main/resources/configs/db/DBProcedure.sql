
#生成bill的储存过程
DELIMITER $
create procedure generateBill()
begin
	set session TRANSACTION ISOLATION LEVEL SERIALIZABLE;
	
	#更新所有Account的状态，未支付账单的状态设为s，其他不变
	
	update Account a,Bill b
	on a.currentBillId=b.id
	set a.state=-1
	where b.isPaid=0
	;
	
	#对非stop的账户生成当前bill的开始时间和结束时间
	declare startDate datetime;
	declare endDate datetime;
	
	select @startDate:=
	
	#更新Account表中
	
end 

$
DELIMITER ;