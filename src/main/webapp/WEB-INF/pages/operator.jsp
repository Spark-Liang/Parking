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
</head>

<style type="text/css">
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
}
.search-inf a{
	font-size:12px;
}
.search-inf p{
	margin-bottom: 0px;
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
		<ol class="breadcrumb">
			<li><a onclick="skip(0)">用户开卡或停卡</a></li>
			<li><a onclick="skip(1)">换卡以及支付帐单</a></li>
		</ol>
		<div class="operator-module1" >
			<div class="operator-module1-1">
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
			<div class="operator-module1-1" >
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
		<div class="operator-module2" style="display: none;" >
			<div class="operator-module1-1 search-show">
				<h3>搜索停车卡信息</h3>
				<div class="col-md-12">
					<form class="form-inline">
						<div class="form-group">
							<label for="exampleInputName2">手机号</label>
							<input type="text" class="form-control input-sm" id="exampleInputName2" placeholder="手机号">
						</div>
						<button type="button" class="btn btn-primary btn-sm search-card">搜索</button>		
					</form>
				</div>
				<div class="alert alert-success col-md-3 search-inf">
					<h4>卡号：<span>44444</span></h4>
					<p>手机号：<span>fds</span></p>
					<p>停车场：<span>fsafda</span></p>
					<p>卡状态：<span>fds</span></p>
					<a onclick="updateCard(this)">更换停车卡</a><br/><a onclick="paymoney(this)">支付帐单</a>
				</div>
			</div>
	</div>
	</div>

	<script type="text/javascript">
		//两个模块之间的跳转
		function skip(num) {
			if (num == 0) {
				$('.operator-module1').show();
				$('.operator-module2').hide();

			} else if (num == 1) {
				$('.operator-module2').show();
				$('.operator-module1').hide();
				
			}
		}
		//页面刷新加载信息
		$(window).ready(function(){
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
		
		
		//点击开卡按钮的操作
		$('.newcard').click(function(){
			var inf = new Array();
			inf[0] = $(this).parent().find('input:eq(0)').val();
			inf[1] = $(this).parent().find('input:eq(1)').val();
			inf[2] = $(this).parent().find('select').val();
			var check = checkinf(this,inf);
			if(check==1){
				 $.ajax({
					url:'user/addNewCard',
					dataType:'json',
					type:'post',
					data:{
						"userId":inf[0],"cardId":inf[1],"LotId":inf[2]
					},success:function(result){
						if(result!=null){
							if(result.falg==1){
								alert(result.message+","+"新的卡号为:"+result.cardId);
							}
							else{
								alert(result.message);
							}		
						}
						else{
							alert(" 系统出错，请联系技术部门！");
						}
					},error:function(result){
						alert(result.message);
					}
				}) 
			}
			//else{
				//alert('fdss')
			//}
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
			var object = /^\d{7}$/;
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
		
		$('.search-card').click(function(){
			var iphone = $(this).parent().find('input:eq(0)').val();
			var object = /^1{1}[3-9]{1}[0-9]{9}$/;
			if(object.test(iphone)){
				$(this).parent().find('input:eq(0)').parent().removeClass('has-error');
				/* $.ajax({
					url:'user/getAllAccount',
					dataType:'json',
					type:'POST',
					data:{
						'userId':iphone
					},success:function(json){
						
						console.log(json);
						var l = json.data.length;
						for(var i =0;i<l;i++){
							$('.search-show').append(function (){
								return "<div class='alert alert-success col-md-3 search-inf'>"
								+"<h4>卡号：<span>"+卡号+"</span></h4>"
								+"<p>手机号：<span>"+卡号+"</span></p>"
								+"<p>停车场：<span>"+卡号+"</span></p>"
								+"<p>卡状态：<span>"+卡号+"</span></p>"
								+"<a>更换停车卡</a><br/><a>支付帐单</a>"
							+"</div>";
							})
						}
					},error:function(){
						
					}
				})  */
			}else{
				alert('手机号格式出错');
				$(this).parent().find('input:eq(0)').parent().addClass('has-error');
			}
		})
		
		//模块2
		//点击更换停车卡按钮触发的事件 a是元素自身
 		function updateCard(a){
			$('.edit-money').remove();
			id = 33;
			content ="请输入手机号码"
			$(a).parent().find('h4').after(function (){
				return NumEdit(id,content);
			});
		}
		
		//点击支付帐单的触发事件 
		function paymoney(){
			
		}
	</script>
</body>

</html>


