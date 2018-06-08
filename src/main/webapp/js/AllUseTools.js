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