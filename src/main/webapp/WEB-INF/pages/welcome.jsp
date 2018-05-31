<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>欢迎页面</title>
    <link rel="stylesheet" href="css/welcome.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>

<style type="text/css">
body{
	background-size: 100%;
	min-width:1000px;
}
img{
	width:100%;
	min-width:1000px;
	height:100%;
	z-index:-5;
	position: fixed;
}

.welcome-mid{
	margin: 0 auto;
	width: 900px;
	height: 800px;
	padding-top:20%
}
.welcome-mid button{
	min-width: 300px;
}
.welcome-mid h1{
	color: white;
	text-align: center;
	margin-top: 20px;
	font-size: 70px;
	font-weight: 600;
}
.welcome-mid p{
	text-align: center;
}
.welcome-mid a{
	color: white;
	font-size: 11px;
}
.welcome-opacity{
	position: absolute;
	background-color: black;
	opacity: 0.5;
	width: inherit;
	height: inherit;
	border-radius: 5px;
	z-index: -1;
	top: 0px;
}
</style>
<body>
	<img src='img/welcome.jpg'>
	<div class="welcome-mid">
		<h1>Welcome Parking manger system</h1>
		<!-- <div class="welcome-opacity"></div> -->
		<a href="user/touserlogin"><button class="btn btn-lg btn-primary center-block">我是用户</button></a>
		<p><a href="inneruser/page">登录后台管理系统</a></p>
	</div>



</body>













