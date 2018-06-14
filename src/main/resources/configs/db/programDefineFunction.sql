use train_db;
DELIMITER $
##############################################################################
##获取给定日期对应的季度的总天数
##############################################################################
drop function if exists getSeasonTotalDays$
create function getSeasonTotalDays(calculateDate date)
returns int
begin
	declare sessonStartDate date;
	declare sessonEndDate date;
	declare last_3_month_date date;
	declare this_month_first_date date;
	
	set @sessonStartDate=
		(select buildDate(tmpYear,tmpMonth-tmpMonth%3+1,1) 
		from 
			(select
				year(calculateDate) tmpYear
				,month(calculateDate) tmpMonth
			)init
		)
	;
	set @sessonEndDate=date_add(@sessonStartDate,interval 3 month);
return(
	
select 
	datediff(@sessonEndDate,@sessonStartDate) 
);
end
$
##############################################################################
##获取给定日期对应的月份的总天数
##############################################################################
drop function if exists getMonthTotalDays$
create function getMonthTotalDays(calculateDate date)
returns int
begin
	declare monthStartDate date;
	declare monthEndDate date;
	declare last_3_month_date date;
	declare this_month_first_date date;
	
	set @monthStartDate=
		(select buildDate(tmpYear,tmpMonth,1) 
		from 
			(select
				year(calculateDate) tmpYear
				,month(calculateDate) tmpMonth
			)init
		)
	;
	set @monthEndDate=date_add(@monthStartDate,interval 1 month);
return(
	
select 
	datediff(@monthEndDate,@monthStartDate) 
);
end
$
drop function if exists getCardUsedDays$
create function getCardUsedDays(stateStartDate date,billGenerateDate date)
returns int
begin
	#计算的中间变量
	declare tmpDate date;
	return 
	(select case 
				#开始日期大于出账单前2个月的第一天，证明中间存在空的天数
				when stateStartDate > date_sub(@tmpDate:=date_sub(billGenerateDate,Interval 2 month),Interval day(@tmpDate) day)
				#计算出账单后一个月的天数，加上重新开始到出账单前的天数
				then datediff(date_sub(@tmpDate:=date_sub(billGenerateDate,Interval 2 month),Interval day(@tmpDate)+1 day)
						,date_sub(@tmpDate:=date_sub(billGenerateDate,Interval 3 month),Interval day(@tmpDate) day))
					+datediff(date_sub(billGenerateDate,Interval 1 day),stateStartDate)
				#其他情况为给季度总天数	
				else getTotalDate(billGenerateDate)
			end
	);
end 
$
drop function if exists buildDate$
create function buildDate(year int,month int,day int)
returns date
begin
	return
	(
		select str_to_date(concat(year,'-',month,'-',day),'%Y-%m-%d')
	);
end 
$