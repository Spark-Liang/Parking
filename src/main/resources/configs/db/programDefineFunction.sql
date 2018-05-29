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
create function buildDate(year int,month int,day int)
returns date
begin
	return
	(
		select str_to_date(concat(year,'-',month,'-',day),'%Y-%m-%d')
	);
end 
$