<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>停车场终端</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<style>
	.module{
		max-width:300px;
	}
</style>
<body>
<div class="module">
	<h3>停车：</h3>
	<form id="parkForm form-gourp">
		<label>停车场id：</label>
		<div class="form-gourp">			
			<input class="form-control" name="parkingLotId" type="text" size="8" /><br>
		</div>
		<label>停车卡号码：</label>
		<div class="form-gourp">		
			<input class="form-control" name="cardId" type="text" size="8" placeholder="请输入7位卡号" /><br>
		</div>
		<!-- <button id="parkCar" >停车</button> -->
		<a class="btn btn-md btn-primary" href="javascript:void(0);" onclick="parkCar(this);">停车</a>
	</form>
</div>
<div class="module">
	<h3>提车：</h3>
	<form id="pickForm form-gourp">
		<label>停车场id：</label>
		<div class="form-gourp">			
			<input class="form-control" name="parkingLotId" type="text" size="8" /><br>
		</div>
		<label>停车卡号码：</label>
		<div class="form-gourp">		
			<input class="form-control" name="cardId" type="text" size="8" placeholder="请输入7位卡号" /><br>
		</div>
		<!-- <button id="pickCar">提车</button> -->
		<a class="btn btn-md btn-primary" href="javascript:void(0);" onclick="pickCar(this);">提车</a>
	</form>
</div>
</body>

<script>
	$(window).ready(function() {
		
	});
	function check(a){
		var parkid = $(a).parent().find('input:eq(0)').val();
		var cardid = $(a).parent().find('input:eq(1)').val();
		var object = /^\d{1,4}$/g;
		var num = 0;
		if(object.test(parkid)){
			$(a).parent().find('input:eq(0)').parent().removeClass('has-error');
		}else{
			alert('不正确的停车场格式');
			$(a).parent().find('input:eq(0)').parent().addClass('has-error');num++;
		}
		var object = /^\d{11}$/g;
		if(object.test(cardid)){
			$(a).parent().find('input:eq(1)').parent().removeClass('has-error');
		}else{
			alert('不正确的停车卡格式');
			$(a).parent().find('input:eq(1)').parent().addClass('has-error');num++;
		}
	}
	function parkCar(a){
		check(a);
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "parkTerminal/park", //提交的地址  
			data : $('#parkForm').serialize(),
			error : function(request) {  
				var msg="";
				for(var key in request){
					msg+=key+":"+request.key;
				}
				alert(msg);
			},
			success : function(response) {
				if(response.error != null && response.error != ""){
					alert("停车失败，原因:"+response.error);
					return;
				}
				if(response.reason != null && response.reason != ""){
					alert("停车失败，原因:"+response.reason);
					return;
				}
				alert("停车成功");
			}
		});
	}
	function pickCar(a){
		check(a);
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "parkTerminal/pick", //提交的地址  
			data : $('#pickForm').serialize(),
			error : function(request) {  
				var msg="";
				for(var key in request){
					msg+=key+":"+request.key;
				}
				alert(msg);
			},
			success : function(response) {
				if(response.error != null && response.error != ""){
					alert("提车失败，原因:"+response.error);
					return;
				}
				if(response.reason != null && response.reason != ""){
					alert("提车失败，原因:"+response.reason);
					return;
				}
				alert("提车成功");
			}
		});
	}
	/* $("#parkCar").click(function(){
		//ajax提交表单
		
		alert(xhr)
	}); */
	/* $("#pickCar").click(function(){
		//ajax提交表单
		
	}); */
</script>
</html>