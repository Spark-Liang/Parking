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

function moneyok(a){
	var id = $(a).data('value');
    var aa = $(a).parent().parent().find('input').val();
   /*  $(a).parent().parent().parent().find('span:eq(0)').text(aa); */
    var object = /^\d{2,5}$/;
        if(object.test(aa)){
        	$.ajax({
                url:'inneruser/changeParkingLotPrice',
                type:'POST',
                dataType:'json',
                data:{
                	'id':id,
                	'currentPrice':aa
                },success:function(json){
                	console.log(json)
                    if(json.msg==1){
                    	alert('修改成功')
                         $(a).parent().parent().parent().find('span:eq(0)').text(aa);
                         $(a).parent().parent().fadeOut();
                    }else if(json.error){
                    	alert('修改失败')
                    }
                },error:function(){

                }
           })
        }else{
    }
     
}
function moneyclose(a){
    $('.edit-money').fadeOut();
}