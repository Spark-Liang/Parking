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
	<form id="parkForm">
		<label>停车场id：</label><input name="parkingLotId" type="text" size="8" /><br>
		<label>停车卡号码：</label><input name="cardId" type="text" size="8" /><br>
		<button id="parkCar" >停车</button>
	</form>
	<p>提车：
	<form action="">
		<label>停车场id：</label><input name="parkingLotId" type="text" size="8" /><br>
		<label>停车卡号码：</label><input name="cardId" type="text" size="8" /><br>
		<button id="pickCar">提车</button>
	</form>

</body>

<script>
	$(window).ready(function() {
		$("#parkCar").click(function(){
			//ajax提交表单
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "parkTerminal/park", //提交的地址  
				data : $('#parkForm').serialize(),
				error : function(request) {  
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
		});
		$("#pickCar").click(function(){
			//ajax提交表单
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "pickTerminal/pick", //提交的地址  
				data : $('#pickForm').serialize(),
				error : function(request) {  
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
		});
	});
</script>
</html>