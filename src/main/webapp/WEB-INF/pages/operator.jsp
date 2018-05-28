<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>管理员管理页面</title>
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>

</head>

<style type="text/css">
.operator-btn{
	width: 180px;
	height: 50px;
	display: inline-block;
	margin-left: 15px;
}
.operator-module1{
	min-height: 280px;
	margin-top: 30px;
	padding-left: 50px;
}
.operator-module1 h3{
	font-size: 22px;
	font-weight: 600;
	border-bottom: 2px solid grey;
	margin-bottom: 20px;
}
.operator-module1 select{
	min-width: 220px;
}
</style>
<script type="text/javascript">
	
</script>
<body>
	<!-- 导航条 -->
	<div class="dh">
		<label>Parking management system</label> <a
			class="btn btn-primary btn-md" href="<%=basePath%>inneruser/logout">退出</a>
	</div>
	<!-- 导航条 -->
	<div class="container container-style">
		<h1 id='admin-name'>Hello,<span>XXX</span></h1>
		<p class="text-info">你的身份是操作员,可以操作以下数据</p>
<!-- 		<a class="operator-btn">
			<button class="btn btn-primary btn-block">用户开卡</button>
		</a>
		<a class="operator-btn">
			<button class="btn btn-danger btn-block">用户停卡</button>
		</a> -->
		<div class="operator-module1">
			<h3>用户开卡</h3>
			<div class="col-md-3">
			    <div class="form-group">
					<label for="exampleInputName2">手机号码</label>
					<input type="text" class="form-control input-sm" id="exampleInputName2" placeholder="请输入正确的手机号格式">
				</div>
				<div class="form-group">
					<label for="exampleInputName2">卡号</label>
					<input type="text" class="form-control input-sm" id="exampleInputName2" placeholder="卡号">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">停车场</label>
					<select class="form-control parkaddress input-sm">
						<option value=""></option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary btn-sm newcard">开卡</button>		
			</div>
		</div>
		<div class="operator-module1" >
			<h3>用户停卡</h3>
			<div class="col-md-3">
			    <div class="form-group">
					<label for="exampleInputName2">手机号码</label>
					<input type="text" class="form-control input-sm" id="exampleInputName2" placeholder="手机号码">
				</div>
				<div class="form-group">
					<label for="exampleInputName2">卡号</label>
					<input type="text" class="form-control input-sm" id="exampleInputName2" placeholder="卡号">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">停车场</label>
					<select class="form-control parkaddress input-sm">
						<option value=""></option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary btn-sm deletecard">停卡</button>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(window).ready(function(){
			var href = decodeURI(window.location.href);
			var adminname = href.split("=")[2];
			alert(name)
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
		
		
		//点击开卡按钮的操作
		$('.newcard').click(function(){
			var inf = new Array();
			inf[0] = $(this).parent().find('input:eq(0)').val();
			inf[1] = $(this).parent().find('input:eq(1)').val();
			inf[2] = $(this).parent().find('select').val();
			var check = checkinf(this,inf);
			if(check == 1){
				/* $.ajax({
					url:'',
					dataType:'json',
					type:'GET',
					data:{
						
					},success:function(){
						
					},error:function(){
						
					}
				}) */
			}
			else{
				alert('fdss')
			}
		})
		//点击停卡按钮的操作
		$('.deletecard').click(function(){
			var inf = new Array();
			inf[0] = $(this).parent().find('input:eq(0)').val();
			inf[1] = $(this).parent().find('input:eq(1)').val();
			inf[2] = $(this).parent().find('select').val();
			var check = checkinf(this,inf);
			if(check == 1){
				/* $.ajax({
					url:'',
					dataType:'json',
					type:'GET',
					data:{
						
					},success:function(){
						
					},error:function(){
						
					}
				}) */
			}
			else{
				
			}
		})
		//检查输入格式是否正确  b是按钮元素 a是传过来的数组
		function checkinf(b,a) {
			var object = /^1{1}[3-9]{1}[0-9]{9}$/;
			var num = 0;//用于判断最后是否全部正确
			if(object.test(a[0])){
				$(b).parent().find('input:eq(0)').parent().removeClass('has-error');
			}else{
				alert('手机格式有误');
				$(b).parent().find('input:eq(0)').parent().addClass('has-error');num++;
			}
			var object = /^1{1}[3-9]{1}[0-9]{9}$/;
			if(object.test(a[1])){
				$(b).parent().find('input:eq(1)').parent().removeClass('has-error');
			}else{
				alert('卡号格式有误');
				$(b).parent().find('input:eq(1)').parent().addClass('has-error');num++
			}
			if(a[2]){
				$(b).parent().find('select').parent().removeClass('has-error');
			}else{
				alert('没有选择停车场')
				$(b).parent().find('select').parent().addClass('has-error');num++;
			}
			if(num==0){
				return 1;
			}else{
				return 0;
			}
		}
	</script>
</body>

</html>


