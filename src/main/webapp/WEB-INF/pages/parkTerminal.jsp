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
	.operator-btn{
		width: 180px;
		height: 50px;
		display: inline-block;
		margin-left: 15px;
	}
	.operator-module1-1{
		min-height: 280px;
		margin-top: 10px;
		padding-left: 50px;
	}
	.operator-module1-1 h3{
		font-size: 22px;
		font-weight: 600;
		border-bottom: 2px solid grey;
		margin-bottom: 20px;
	}
	.operator-module1-1 select{
		min-width: 220px;
	}
	.search-inf{
		margin-top:30px;
		margin-right:20px;
		min-height: 180px;
	}
	.search-inf a{
		font-size:12px;
	}
	.search-inf p{
		margin-bottom: 0px;
	}
	.pay-money{
		z-index:1000;
		width: 300px;
		height: 250px;
		top:30%;
		left:35%;
		position:fixed;
		display:none;
	}
	.pay-money h4{
		text-align:center;
		font-weight:600;
	}
</style>

<body>
<div class="module">
	<h3>停车：</h3>
	<form id="parkForm" class="parkForm form-gourp">
		<div class="form-group">
			<label for="exampleInputEmail2">停车场</label>
			<select class="form-control parkaddress input-sm" name="lotId">
				<option value=""></option>
			</select>
		</div>
		<label>停车卡号码：</label>
		<div class="form-gourp">		
			<input class="form-control" name="cardId" type="text" size="8" placeholder="请输入7位卡号,从0000001开始" /><br>
		</div>
		<!-- <button id="parkCar" >停车</button> -->
		<a class="btn btn-md btn-primary" href="javascript:void(0);" onclick="parkCar(this);">停车</a>
	</form>
</div>

<div class="module">
	<h3>提车：</h3>
	<form id="pickForm" class="pickForm form-gourp">
		<div class="form-group">
			<label for="exampleInputEmail2">停车场</label>
			<select class="form-control parkaddress input-sm" name="lotId">
				<option value=""></option>
			</select>
		</div>
		<label>停车卡号码：</label>
		<div class="form-gourp">		
			<input class="form-control" name="cardId" type="text" size="8" placeholder="请输入7位卡号,从0000001开始" /><br>
		</div>
		<!-- <button id="pickCar">提车</button> -->
		<a class="btn btn-md btn-primary" href="javascript:void(0);" onclick="pickCar(this);">提车</a>
	</form>
</div>
</body>

<script>
	$(window).ready(function() {
		var href = decodeURI(window.location.href);
		var adminname = href.split("=")[2];
		$('#admin-name span').text(adminname);
		//获取停车场的信息到选择框中
		$.ajax({
			url:'parkinglot/list',
			type:'GET',
			dataType:'json',
			data:{

			},success:function(data){
				console.log(data.res);
				var l = data.res.length;
				for(var i = 0;i<l;i++){
					$('.parkaddress').append(function(){
						return "<option value='"+data.res[i].id+"'>"+data.res[i].name+"</option>";
					})
				}
				
			},error:function(){

			}
		})
	});
	function check(a){
		//var parkid = $(a).parent().find('input:eq(0)').val();
		var cardid = $(a).parent().find('input:eq(0)').val();
		var object = /^\d{1,4}$/g;
		var num = 0;
		/* if(object.test(parkid)){
			$(a).parent().find('input:eq(0)').parent().removeClass('has-error');
		}else{
			alert('不正确的停车场格式');
			$(a).parent().find('input:eq(0)').parent().addClass('has-error');num++;
		} */
		var object = /^\d{7}$/g;
		if(object.test(cardid) && cardid>0){
			$(a).parent().find('input:eq(1)').parent().removeClass('has-error');
		}else{
			alert('不正确的停车卡格式');
			$(a).parent().find('input:eq(1)').parent().addClass('has-error');num++;
		}
		if(num>0){
			return false;
		}else{
			return true;
		}
	}
	function parkCar(a){
		if(check(a)){
			console.log( $('#parkForm').serialize());
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
	}
	function pickCar(a){
		if(check(a)){
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