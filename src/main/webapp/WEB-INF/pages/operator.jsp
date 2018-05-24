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
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="js/jquery-3.3.1.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

</head>

<style type="text/css">
.operator-btn{
	width: 180px;
	height: 50px;
	display: inline-block;
	margin-left: 15px;
}
.operator-module1{
	min-height: 180px;
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
	min-width: 200px;
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
			<form class="form-inline">
				<div class="form-group">
					<label for="exampleInputName2">手机号码</label>
					<input type="text" class="form-control" id="exampleInputName2" placeholder="手机号码">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">停车场</label>
					<select class="form-control">
						<option value=""></option>
						<option value="">放大分</option>
						<option value=""></option>
						<option value=""></option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
			
		</div>
		<div class="operator-module1">
			<h3>用户停卡</h3>
			<form class="form-inline">
				<div class="form-group">
					<label for="exampleInputName2">手机号码</label>
					<input type="text" class="form-control" id="exampleInputName2" placeholder="手机号码">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">停车场</label>
					<select class="form-control">
						<option value=""></option>
						<option value="">放大分</option>
						<option value=""></option>
						<option value=""></option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		$(window).ready(function(){
			$.ajax({
				url:'parkinglot/list',
				type:'GET',
				dataType:'json',
				data:{

				},success:function(data){
					console.log(data);
				},error:function(){

				}
			})
		})
	</script>
</body>

</html>


