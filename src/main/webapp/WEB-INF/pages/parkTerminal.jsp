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
<script src="js/jquery-3.3.1.js"></script>
</head>
<body>
	<p>停车：
	<form action="">
		<label>停车场id：</label><input name="parkingLotId" type="text" size="8" /><br>
		<label>停车卡号码：</label><input name="cardId" type="text" size="8" /><br>
		<button id="parkCar" type="submit">停车</button>
	</form>
	<p>提车：
	<form action="">
		<label>停车场id：</label><input name="parkingLotId" type="text" size="8" /><br>
		<label>停车卡号码：</label><input name="cardId" type="text" size="8" /><br>
		<button id="pickCar" type="submit">提车</button>
	</form>

</body>

<script>
	$(window).ready(function() {
		alert("停车成功")
		alert("停车失败")
	})
</script>
</html>