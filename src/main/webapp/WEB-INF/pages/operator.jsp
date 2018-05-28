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
	min-height: 230px;
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
					<input type="text" class="form-control" id="exampleInputName2" placeholder="手机号码">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">停车场</label>
					<select class="form-control parkaddress">
						<option value=""></option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>		
			</div>
		</div>
		<div class="operator-module1" >
			<h3>用户停卡</h3>
			<div class="col-md-3">
			    <div class="form-group">
					<label for="exampleInputName2">手机号码</label>
					<input type="text" class="form-control" id="exampleInputName2" placeholder="手机号码">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">停车场</label>
					<select class="form-control parkaddress">
						<option value=""></option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>
			<div class="col-md-6">
			fds
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(window).ready(function(){
			var adminname = window.location.href.split("=")[2];
			$('#admin-name span').text(adminname);
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
		})
	</script>
</body>

</html>


