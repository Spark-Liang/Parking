function NumEdit(id,content) {
    	return "<div class='input-group input-group-sm edit-money'>"
        +"<input type='number' class='form-control' placeholder='"+content+"'>"
        +"<span class='input-group-btn'>"
        +"<button class='btn btn-default'  data-value='"+id+"' type='button' onclick='moneyok(this)'><img src='img/manger-editor.svg'></button>"
        +"</span>"
        +"<span class='input-group-btn'>"
        +"<button class='btn btn-default' type='button' onclick='moneyclose(this)'><img src='img/manger-close.svg'></button>"
        +"</span>"
    +"</div>";
	}


function moneyclose(a){
    $('.edit-money').fadeOut();
}

function useMuchDay(day,month){
	if(month == 1||month == 4 || month == 7 || month == 10){
		return day;
	}else if(month == 2){
		return day+31;
	}else if(month == 3){
		return day+59;
	}else if(month == 5){
		return day+30;
	}else if(month == 6){
		return day+61;
	}else if(month == 8){
		return day+31;
	}else if(month == 9){
		return day+62;
	}else if(month == 11){
		return day+30;
	}else if(month == 12){
		return day+61;
	}
}

//根据第几个月返回第几季度
function allDay(month){
	var allday = new Array();
	if(month<3){
		allday[0]=90;
		allday[1]=1;
		return allday;
	}else if(3<month<7){
		allday[0]=91;
		allday[1]=4;
		return allday;
	}else if(6<month<10){
		allday[0]=92;
		allday[1]=7;
		return allday;
	}else if(9<month<=12){
		allday[0]=91;
		allday[1]=10;
		return allday;
	}
}

//根据月份返回该月的天数
function getMonth(month){
	if(month == 1||month == 3|| month== 5 || month == 7 || month == 8 ||month == 10 ||month == 12){
		return 31;
	}else if(month ==2){
		return 28;
	}else if(month == 4 || month == 6|| month == 9 || month == 11){
		return 30;
	}
}




