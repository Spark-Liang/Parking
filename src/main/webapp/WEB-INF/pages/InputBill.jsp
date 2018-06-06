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
<script src="js/AllUseTools.js"></script>
<script src="js/aopDefind.js"></script>
</head>

<style type="text/css">

</style>
<script type="text/javascript">
	
</script>
<body>
	<input type="text"  class="form-control input-sm" id="exampleInputName2" placeholder="手机号">
	<input type="text"  class="form-control input-sm" id="exampleInputName2" placeholder="停车场id">
	<input type="text"  class="form-control input-sm" id="exampleInputName2" placeholder="账户id">
	<input type="text"  class="form-control input-sm" id="exampleInputName2" placeholder="价格">
	<input type="checkbox" name="pay"  class="form-control input-sm"  value="1">已经支付
	<input type="checkbox" name="pay" class="form-control input-sm"  value="0">没有支付
	<button class="btn btn-primary btn-sm">提交</button>
	
	<script type="text/javascript">
		var iphone = $('input:eq(0)').val();
		var pardid = $('input:eq(1)').val();
		var accountid = $('input:eq(2)').val();
		var price = $('input:eq(3)').val();
		var price = $('input[name="pay"]:checked').val();
		var myData = new Data();
		console.log(myData);
	</script>
</body>

</html>


