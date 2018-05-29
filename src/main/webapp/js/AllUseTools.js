function NumEdit(id) {
    	return "<div class='input-group input-group-sm edit-money'>"
        +"<input type='number' class='form-control' placeholder='Parking money'>"
        +"<span class='input-group-btn'>"
        +"<button class='btn btn-default'  data-value='"+id+"' type='button' onclick='moneyok(this)'><img src='img/manger-editor.svg'></button>"
        +"</span>"
        +"<span class='input-group-btn'>"
        +"<button class='btn btn-default' type='button' onclick='moneyclose(this)'><img src='img/manger-close.svg'></button>"
        +"</span>"
    +"</div>";
	}