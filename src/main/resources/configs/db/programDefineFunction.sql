DELIMITER $
create function getTotalDate(billDate date)
returns int
begin
	declare last_3_month_date date;
	declare this_month_first_date date;
return(
select 
	datediff(dateEnd,dateStart) 
from 
	(select 
		#获取当前月份对应的上一季度开始天
		@last_3_month_date:=date_sub(billdate,Interval 3 month) as last_3_month_date
		,date_sub(@last_3_month_date,Interval day(@last_3_month_date) day) dateStart
		#获取当前月份对应的上一季度结束天
		,@this_month_first_date:=date_sub(billdate,Interval day(billdate) day)
		 this_month_first_date
		,date_sub(@this_month_first_date,Interval 1 day) dateEnd
	)tmp
);
end
$
create function getCardUsedDays(stateStartDate date,billGenerateDate date)
returns int
begin
	#计算的中间变量
	declare tmpDate date;
	return 
	(select case 
				#开始日期大于出账单前2个月的第一天，证明中间存在空的天数
				when stateStartDate>(date_sub(@tmpDate:=date_sub(billGenerateDate,Interval 2 month),Interval day(@tmpDate) day)
				#计算出账单后一个月的天数，加上重新开始到出账单前的天数
				then datediff(date_sub(@tmpDate:=date_sub(billGenerateDate,Interval 2 month),Interval day(@tmpDate)+1 day)
						,date_sub(@tmpDate:=date_sub(billGenerateDate,Interval 3 month),Interval day(@tmpDate) day))
					+datediff(date_sub(billGenerateDate,Interval 1 day),stateStartDate)
				#其他情况为给季度总天数	
				else getTotalDate(billGenerateDate)
			end
	)
end 
$
create function buildDate(year int,month int,day int)
returns date
begin
	return
	(
		select str_to_date(concat(year,'-',month,'-',day),'%Y-%m-%d')
	);
end 
$