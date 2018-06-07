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
<script src="js/laydate.js"></script> 

</head>

<style type="text/css">
body{padding: 20px;}
  .demo-input{padding-left: 10px; height: 38px; min-width: 262px; line-height: 38px; border: 1px solid #e6e6e6;  background-color: #fff;  border-radius: 2px;}


</style>
<script type="text/javascript">
	
</script>
<body>
<div class="col-md-4">
	手机号
	<input type="text"  class="form-control input-sm" id="exampleInputName2" placeholder="手机号">
	停车场id
	<input type="text"  class="form-control input-sm" id="exampleInputName2" placeholder="停车场id">
	账户id
	<input type="text"  class="form-control input-sm" id="exampleInputName2" placeholder="账户id">
	价格
	<input type="text"  class="form-control input-sm" id="exampleInputName2" placeholder="价格">
	<input type="radio" name="pay" class="form-control input-sm"  value="1">已经支付
	<input type="radio" name="pay" class="form-control input-sm"  value="0">没有支付
	<br/>
	<br/>
	<br/>
	开始日期
	<input type="text"  class="demo-input" placeholder="开始日期" id="test31">
	<br/><br/>
	结束日期
	<input type="text"  class="demo-input" placeholder="结束日期" id="test30">
		<br/><br/>
	<button class="btn btn-primary btn-sm">提交</button>
	
</div>
		
	<script type="text/javascript">
		$('button').click(function(){
			var iphone = $('input:eq(0)').val();
			var parkid = $('input:eq(1)').val();
			var accountid = $('input:eq(2)').val();
			var price = $('input:eq(3)').val();
			var pay = $('input[name="pay"]:checked').val();
			var startdate = $('#test31').val();
			var enddate = $('#test30').val();
			alert(enddate)
			$.ajax({
				url:'user/insertBill',
				type:'POST',
				dataType:'json',
				data:{
					'userId':iphone,
					'parkingLotId':parkid,
					'accountId':accountid,
					'price':price,
					'isPaid':pay,
					'billStartDate':startdate,
					'billEndDate':enddate,
				},success:function(json){
					console.log(json);
				},error:function(json){
					console.log(json);
				}
			});
		})
		
		//年月选择器
		lay('#version').html('-v'+ laydate.v);

	//格子主题
    laydate.render({
      elem: '#test31'
      ,theme: 'grid'
    });
  //格子主题
    laydate.render({
      elem: '#test30'
      ,theme: 'grid'
    });


	</script>
</body>

</html>


