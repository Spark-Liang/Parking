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
<title>操作员操作页面</title>
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/AllUseTools.js"></script>
<script src="js/aopDefind.js"></script>
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
}
.search-inf{
	margin-top:30px;
	margin-right:20px;
	min-height: 150px;
	border: 2px solid black;
	border-radius:5px;
}
.search-inf a{
	font-size:12px;
	margin-top:10px;
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
	border:1px solid black;
	background-color:white;
}
.pay-money h4{
	text-align:center;
	font-weight:600;
}
.add-user{
	display:none;
}
.tbody-add tr{
	height:47px;
}
.payBill-btn{
	margin-right: 5px;
}
</style>
<script type="text/javascript">
	
</script>
<body>
	<div class="pay-money alert">
		<h4>确认支付帐单</h4>
		<p>卡号：<span>gfd</span><p>
		<p>停车场：<span></span><p>
		<h3>支付金额：<span>fds</span>元</h3>
		<br/>
		<a class="btn btn-block btn-primary" data-value="0" data-state="0" onclick="paybill(this)">支付</a>
		<a class="btn btn-block btn-primary">取消</a>
	</div>
	<!-- 导航条 -->
	<div class="dh">
		<label>Parking management system</label> <a
			class="btn btn-primary btn-md" href="<%=basePath%>inneruser/logout">退出</a>
	</div>
	<!-- 导航条 -->
	<div class="container container-style">
		<h1 id='admin-name'>Hello,<span>XXX</span></h1>
		<p class="text-info">您的身份是操作员,可以操作以下数据</p>
		<ol class="breadcrumb">
			<li><a onclick="skip(0,this)">用户开卡</a></li>
			<li><a onclick="skip(1,this)">用户停卡</a></li>
			<li><a onclick="skip(2,this)">换卡以及支付帐单</a></li>
		</ol>
		<div class="operator-module1" >
			<div class="operator-module1-1">
				<h3>用户开卡</h3>
				<div class="col-md-3">
				    <div class="form-group">
						<label for="exampleInputName2">手机号码</label>
						<input type="text" class="form-control input-sm" id="exampleInputName2" placeholder="请输入正确的手机号格式 ">
					</div>
					<div class="form-group">
						<label for="exampleInputName2">卡号</label>
						<input type="text" class="form-control input-sm" id="exampleInputName2" placeholder="请输入7位卡号">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">停车场</label>
						<select class="form-control parkaddress input-sm">
							<option value=""></option>
						</select>
					</div>
					<button type="submit" class="btn btn-primary btn-sm newcard">开卡</button>		
				</div>
				<div class="col-md-3 add-user col-md-offset-1">
					<label for="exampleInputName2">新增用户</label>
					<p>手机号：<span></span></p>
					<div class="form-group">
						<input class="form-control input-sm" id="" placeholder="请输入6位数以上的密码" type="password">
					</div>
					<button class="btn btn-primary btn-sm addUserBtn">确定</button>
				</div>
			</div>
			
		</div>
		<div class="operator-module2" style="display: none;">
			<div class="operator-module1-1" >
				<h3>用户停卡</h3>
				<div class="col-md-3">
				    <div class="form-group">
						<label for="exampleInputName2">手机号码</label>
						<input type="text"  class="form-control inut-sm" id="exampleInputName2" placeholder="请输入正确的手机号格式">
					</div>
					<div class="form-group">
						<label for="exampleInputName2">卡号</label>
						<input type="text" class="form-control input-sm stop-card" id="exampleInputName2" placeholder="请输入7位卡号">
					</div>
					<button type="submit" class="btn btn-primary btn-sm deletecard">停卡</button>
				</div>
				<div class="col-md-7 col-md-offset-1 payBill" style="display: none">
					<h4><strong>支付账单信息</strong></h4>
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="col-md-3">手机号</th>
								<th class="col-me-5">账单时间</th>
								<th class="col-me-1">价格</th>
								<th>账单金额</th>
							</tr>
						</thead>
						<tbody class="BillTable">
							<!--  -->
						</tbody>
					</table>
					<button class="btn btn-primary btn-sm pull-right payBill-close">关闭</button>
					<button class="btn btn-primary btn-sm pull-right payBill-btn">支付账单并停卡</button>
				</div>
			</div>
		</div>
		<div class="operator-module3" style="display: none;" >
			<div class="operator-module1-1 search-show">
				<h3>搜索停车卡信息</h3>
				<div class="col-md-12">
					<form class="form-inline">
						<div class="form-group">
							<label for="exampleInputName2">手机号</label>
							<input type="text" class="form-control input-sm input-userId" id="exampleInputName2" placeholder="手机号">
						</div>
						<button type="button" class="btn btn-primary btn-sm search-card">搜索</button>		
					</form>
				</div>
				<br/><br/>
				<div class="col-md-10">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th class="col-md-1">卡号</th>
								<th class="col-md-3"></th>
								<th class="col-md-2">停车场</th>
								<th class="col-md-2">手机号</th>
								<th class="col-md-2">状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="tbody-add">
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
	</div>

	<script type="text/javascript">
	/* $(function (){
		//ajax开始的时候出发的函数
		$('.newcard').ajaxStart(function(){
			$(this).hide();
		});
	}) */
	
	
		//两个模块之间的跳转
		function skip(num,a) {
			$('.breadcrumb a').css({'font-weight':'500'})
			if (num == 0) {
				fontWeight();
				$('.operator-module1').show();
				$('.operator-module2').hide();
				$('.operator-module3').hide();

			} else if (num == 1) {
				fontWeight();
				$('.operator-module2').show();
				$('.operator-module1').hide();
				$('.operator-module3').hide();
				
			} else if(num == 2){
				fontWeight();
				$('.operator-module3').show();
				$('.operator-module1').hide();
				$('.operator-module2').hide();
			}
			function fontWeight(){
				$(a).css({'font-weight':'600'})
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
					url:'user/addNewCard',//检测新卡是否已经存在
					dataType:'json',
					type:'post',
					data:{
						"userId":inf[0],"cardId":inf[1],"LotId":inf[2]
					},success:function(result){
						//con为真的情况下
						console.log(result);
						if(result!=null){
							if(result.falg==1){
								/* alert(result.message); */
								if(confirm(result.message)){
									$.ajax({
										url:'user/addCard',//增加 新卡 
										dataType:'json',
										type:'post',
										data:{
											"userId":inf[0],"cardId":inf[1],"LotId":inf[2]
										},success:function(data){
											alert(data.message+","+"新的卡号为:"+data.cardId);
										},error:function(){
											
										}
									});
								}
								
							}
							else{						
								if(result.falg==3){
									var con = confirm('该用户不存在，是否停止开卡并新增用户？');
									if(con){
										$('.add-user').show();
										$('.add-user span').text(inf[0]);
									}
								}
								else{
									alert(result.message);
								}
								
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
		//数据库中没有用户操作员帮助用户注册
		$('.addUserBtn').click(function(){
			var iphone = $(this).parent().find('span').text();
			var password = $(this).parent().find('input').val();
			var object = /^\w{6,12}$/g;
			if(object.test(password)){
				$(this).parent().find('input').parent().removeClass('has-error');
				$.ajax({
					url:'user/addNewUser',
					dataType:'json',
					type:'POST',
					data:{
						'userId':iphone,
						'password':password
					},success:function(json){
						console.log(json);
						if(json.status){
							$('.addUserBtn').parent().hide();
							alert('成功新增用户,请重新操作开卡')
						}
						
					},error:function(){
						
					}
				})
			}else{
				alert('密码格式有问题！');
				$(this).parent().find('input').parent().addClass('has-error');
			}
 		})
 		
 		
 		/*                                     */
 		//操作员帮助用户进行停卡模块的方法分割
		//点击停卡按钮的操作
		$('.deletecard').click(function(){
			var inf = new Array();
			inf[0] = $(this).parent().find('input:eq(0)').val();
			inf[1] = $(this).parent().find('input:eq(1)').val();
			inf[2] = 1;
			var check = checkinf(this,inf);
			if( check == 1){
				 var a = checkBill(inf); //a用与接收返回值 并用于判断能否进行停卡 checkBill 查未支付账单
			}
			else{
				
			}
		})
		//停卡的时候点击停卡按钮先通过checkBill函数进行判断
		//需要进行判断的有这个账户是否和停车卡匹配
		//然后判断是否存在未支付的账单
		function checkBill(a){
			$('.payBill').hide();
			$('.BillTable tr').remove();
				$.ajax({
					url:'user/getAllAccount',
					dataType:'json',
					type:'POST',
					data:{
						'userId':a[0],
						'cardId':a[1],
						'isGetAll':'false'
					},success:function(json){
						console.log(json);
						if(json.list==null){
							alert(json.message);
						}else{
							if(json.list.length==0){
								alert('卡号不存在');
								return 0;
							}else{
								return checkBillData(json);
							}
							
						}
					},error:function(){
						alert('提交出错')
					}
				})
		}
		/*用于判断checkBill中的数据 
		  json是传过来的json数据
		  return 0是不允许提交 1是可以提交
		     在没有支付账单的情况下 
		    显示用户的账单信息
		    返回0表示不可以支付账单 1表示可以支付账单
		    currentBill是数组
		*/
		function checkBillData(json){
			accountId = json.list[0].id;
			var currentBill = json.list[0].currentBill;
			console.log(accountId)
			console.log(currentBill.length)
			//////////////////////////////////////////////
			if(json.list[0].parking==true){
				alert('此卡在停车场中已经被使用需要先进行提车')
			}else{
				if(currentBill.length!=0){//currentBill长度不为0的时候表示有未支付的账单
					payBills(0,currentBill.length);
				}else{
					payBills(1,currentBill.length);//1表示没有上个月未支付的账单，但是需要支付这个月的账单。
				}
			}
			///////////////////////////////////////
			/* if(currentBill.length!=0&&json.list[0].parking==true){//存在账单但是没有支付,显示两个账单
			    alert('此卡在停车场中已经被使用需要先进行提车')
				payBills(0,currentBill.length);
				return 0;
			}else if(currentBill.length!=0&&json.list[0].parking==false){
				payBills(0,currentBill.length);
				return 0;
			}else if (currentBill.length==0&&json.list[0].parking==true) {//currentBill长度不为0的时候表示只有当前季度的费用
				alert('此卡在停车场中已经被使用需要先进行提车');
				return 0;
			}else if(currentBill.length==0){
				payBills(1,currentBill.length);//1表示没有上个月未支付的账单，但是需要支付这个月的账单。
			}else{
				var con = confirm('是否进行停卡?')
				if(con){//没有账单以及没有停车的情况下直接进行停卡
					var billid = $('.stop-card').val();
					console.log(billid)
					$.ajax({
							url:'user/stopCard',
							dataType:'json',
							type:'POST',
							data:{
								'cardId':billid
							},success:function(json){
								if(json.flag==1){
									alert('停卡成功')
								}
							},error:function(){
								
							}
						})
				}else{
					alert('取消停卡')
				}
			} */
			/////////////////////////////////////////
			//payBills函数中传过来的值是用于判断是存在账单还是不存在账单但是需要支付的状况
			//用于显示用户的账单信息
			//payBIlls函数用于显示账单信息
			function payBills(num,l){
				$('.payBill').show();
				console.log(l)
				if (num == 0 ){
					for (var i = 0;i<l;i++){//循环用于显示多少张账单
						var sDate = new Date(json.list[0].currentBill[i].timeQuantums[0].startTime);
						var sDate1 = sDate.toLocaleString('chinese',{hour12:false});
						var startDate = sDate1.substring(0,9);
						var eDate = new Date(json.list[0].currentBill[i].timeQuantums[0].endTime);
						var eDate1 = eDate.toLocaleString('chinese',{hour12:false});
						var endDate = eDate1.substring(0,9);
						var price = Math.ceil(json.list[0].currentBill[i].price);
						$('.BillTable').append(function(){
							return "<tr data-value='"+json.list[0].cardId+"'>"
							+"<td>"+json.list[0].userId+"</td>"
							+"<td>"+startDate+"~"+endDate+"</td>"
							+"<td>"+json.list[0].price+" ￥/每月</td>"
							+"<td>"+price+"</td>"
							+"</tr>"
						});
					}
					if(l==1){//当长度为1的时候显示当前季度的账单
						currentBill(l);
					}
				}else if(num == 1){
					currentBill(l);
				}
				
				//currentBill函数用于显示当前季度的费用
				function currentBill(l){
					console.log(accountId)
					$.ajax({
						url:'bill/newBillInfo',
						type:'GET',
						dataType:'json',
						data:{
							'accountId':accountId
						},success:function(time){
							console.log(time);
							var length = time.stateLogs.length;
							if(length == 2){
									var startTime = new Date(time.stateLogs[0].startTime);//获取账单的开始时间
								 	startTime = startTime.toLocaleString('chinese',{hour12:false});
								 	startTime = startTime.substring(0,9);//获取结束时间
								 	var endTime = new Date(time.stateLogs[0].endTime);//获取账单的开始时间
								 	endTime = endTime.toLocaleString('chinese',{hour12:false});
								 	endTime = endTime.substring(0,9);//获取结束时间
								 	$('.BillTable').append(function(){
										return "<tr data-value='"+json.list[0].cardId+"'>"
										+"<td>"+json.list[0].userId+"</td>"
										+"<td>"+startTime+" ~ "+endTime+"</td>"
										+"<td>"+time.price+" ￥/每月</td>"
										+"<td>"+time.price+" 元</td>"
										+"</tr>"
									});
								 	getMonthDays(json,1);
								 	
							}else{
								getMonthDays(json,0);
							}
						function getMonthDays(json,i){
							var data = new Date(time.stateLogs[i].startTime);//获取账单的开始时间
							var startTime = data.toLocaleString('chinese',{hour12:false});
							var startTime = startTime.substring(0,9);//获取结束时间
							console.log(startTime);
							var date = new Date();//获取账单的当前时间
							var nowTime = date.toLocaleString('chinese',{hour12:false});
							var nowTime = nowTime.substring(0,9);//获得当前时间
							var day = date.getDate();
							var days = getDayNum(startTime,nowTime);//getDayNum方法用于获取开始时间到当前时间的天数
							var month = date.getMonth()+1;//获取当前月份
							var year = date.getFullYear();//获取当前年份
							var allDayNum = allDay(month);//allDay方法用于获取当前月的季度以及季度的总天数  方法写在AllUseTool.js中
							/* var useDay = useMuchDay(day,month);*/
							if(l==0){
								var price = (time.price * 3)*(days/allDayNum[0]);//allDayNum[0]是表示总天数 [1]是表示第几季度
							}else if(l==1){//l 等于1 的时候表示存在一张账单 并且此季度有可能存在费用
								if(month!=1&&month!=4&&month!=7&&month!=10){
									if(month == 5||month==6){//如果是5 6 月 那此季度第一个月一定是四月
										days==30;
									}else{//否则就是1 7 10 月
										days==31;
									}
									var price = (time.price * 3)*(days/allDayNum[0]);//allDayNum[0]是表示总天数 [1]是表示第几季度
								}else{
									var price = (time.price * 3)*(days/allDayNum[0]);//allDayNum[0]是表示总天数 [1]是表示第几季度
									
								}
							}
							var price = Math.ceil(price);//把价格向上取整
							$('.BillTable').append(function(){
								return "<tr data-value='"+json.list[0].cardId+"'>"
								+"<td>"+json.list[0].userId+"</td>"
								+"<td>"+startTime+" ~ "+nowTime+"</td>"
								+"<td>"+time.price+" ￥/每月</td>"
								+"<td>"+price+" 元</td>"
								+"</tr>"
							})
						}
							
						},error:function(){
								
						}
					})
					/* 用于判断两个日期之间有多少天并返回天数 */
					function getDayNum(startTime,nowTime){
						startTime = new Date(startTime);
						startTime = startTime.getTime()
						nowTime = new Date(nowTime)	;
						nowTime = nowTime.getTime();
						console.log(nowTime-startTime);
						var time = nowTime - startTime;
						var days=Math.floor(time/(24*3600*1000));
						return days+1;
					}
				}
				
			}
		}
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
		//停卡时候有未支付账单的时候点击支付账单触发的方法
		$('.payBill-btn').click(function (){
			var con = confirm('确认已经收取客户现金');
			if(con){
				var billid = $('.BillTable tr').data("value");
				console.log(billid);
				$.ajax({
						url:'user/stopCard',
						dataType:'json',
						type:'POST',
						data:{
							'cardId':billid
						},success:function(json){
							if(json.flag==1){
								alert('支付成功以及停卡成功');
								$('.payBill').hide();
							}
						},error:function(){
							
						}
					})
			}else{
				alert('取消支付账单以及停卡')
			}
			
		});
		//点击关闭的按钮
		$('.payBill-close').click(function (){
		 	$('.payBill').hide();
		})
		
		
		
		
		
		//操作员搜索停车卡信息模块的开始
		$('.search-card').click(function(){
			$('.tbody-add tr').remove();
			var iphone = $(this).parent().find('input:eq(0)').val();
			var object = /^1{1}[3-9]{1}[0-9]{9}$/;
			if(object.test(iphone)){
				$(this).parent().find('input:eq(0)').parent().removeClass('has-error');
				searchCard(iphone);
			}else{
				alert('手机号格式出错');
				$(this).parent().find('input:eq(0)').parent().addClass('has-error');
			}
		})
		//根据用户id 搜索用户信息
		function searchCard(iphone){
			$.ajax({
				url:'user/getAllAccount',
				dataType:'json',
				type:'POST',
				data:{
					'userId':iphone,
					'isGetAll':'true'
				},success:function(json){
					console.log(json);
					/* conslog.log(json.list[i].currentBill) */
					if(json.message==''){
						var l = json.list.length;
						for (var i = 0 ;i<l;i++){
							if (json.list[i].currentBill!=null){
								if(json.list[i].currentBill.paid!=true){
									billid = json.list[i].currentBill.id;
									price = json.list[i].currentBill.price;
								}else{
									billid = 0;
									price = 0;
								}
							}else{
								billid = 0;
								price = 0;
							}
							var cardId = json.list[i].cardId;
							if (json.list[i].cardId==null){//判断卡号是否存在 方便显示
								var cardId = '已停卡';
							}
							//获取用户账户的状态
							var state = json.list[i].state;
							//终止使用的情况下隐藏两个按钮 欠费停卡隐藏换卡按钮 正常使用不隐藏按钮
							if(state == 'FREEZE'){
								state = '<p class="text-danger">终止使用</p>';
								var state1 = 'hidden';
								var state2 = state1;
							}else if(state == 'STOP'){
								state = '<p class= "text-warning">欠费停卡</p>';
								var state1 = 'hidden';
								var state2 = '';
							}else if(state == 'NORMAL'){
								state = '正常使用'
								var state1 = '';
								var state2 = '';
							}
							$('.tbody-add').append(function (arr){
								return "<tr>"
								+"<td>"+cardId+"</td>"
								+"<td></td>"
								+"<td>"+json.list[i].parkingLot.name+"</td>"
								+"<td>"+json.list[i].userId+"</td>"
								+"<td>"+state+"</td>"
								+"<td>"
								+"<a class='btn btn-default btn-xs "+state1+"'  data-value='"+json.list[i].cardId+"' onclick='updateCard(this)'>更换停车卡</a>"
								+"<a class='btn btn-default btn-xs "+state2+"' onclick='paymoney(this)' data-billid='"+billid+"' data-cardid='"+json.list[i].cardId+"' data-park='"+json.list[i].parkingLot.name+"' data-price='"+price+"' data-state='"+json.list[i].state+"'>支付帐单</a>"
								+"</td>"
							+"</tr>";
							});
						}
					}else{
						alert(json.message);
					}
				},error:function(){
					alert('系统出错')
				}
			})
		}
		//模块2
		//点击更换停车卡按钮触发的事件 a是元素自身
 		function updateCard(a){
			$('.edit-money').remove();
			
			var id = $(a).parent().parent().find('td:eq(0)').text();
			console.log(id);
			content ="请输入新的卡号"
			$(a).parent().parent().find('td:eq(1)').append(function (){
				return NumEdit(id,content);
			});
		}
		function moneyok(a){
			var OldCardId = $(a).data('value');
		    var NewCardId = $(a).parent().parent().find('input').val();
		    var userId = $('.input-userId').val();
		    var object = /^\d{7}$/g;
		  // $(a).parent().parent().parent().find('span:eq(0)').text(aa);
		    var object = /^\d{7}$/;
		        if(object.test(NewCardId)){
		        	$(a).parent().parent().find('input').parent().removeClass('has-error');
		        	$.ajax({
		                url:'user/changeCard',
		                type:'POST',
		                dataType:'json',
		                data:{
		                	'OldCardId':OldCardId,
		                	'NewCardId':NewCardId,
		                	'userId':userId
		                },success:function(json){
		                	console.log(json)
		                    if(json.flag==1){
		                    	alert('修改成功');
		                         $(a).parent().parent().parent().parent().find('td:eq(0)').text(NewCardId);
		                         $(a).parent().parent().fadeOut();
		                    }else if(json.flag==0){
		                    	alert(json.message);
		                    }
		                    else if(json.error){
		                    	alert('修改失败');
		                    }
		                },error:function(){
		                	alert('更换停车卡出错！')
		                }
		           })
		        }else{
		        	alert('停车卡格式有问题！！');
		        	$(a).parent().parent().find('input').parent().addClass('has-error');
		        }  
		}
		//下面两个方法都是操作员搜索停车卡信息模块的
		//点击支付帐单的触发事件 
		function paymoney(a){
			var billId = $(a).data("billid");
			var state = $(a).data("state");
			console.log(billId);
			if(billId == 0){
				alert('当前停车卡没有需要支付的账单');
			}
			else{
				var park = $(a).data("park");
				var price = $(a).data("price");
				var price1 = price.toString().substring(0,3)
				var cardid = $(a).data("cardid");
				$('.pay-money').fadeIn();
				$('.pay-money span:eq(0)').text(cardid);
				$('.pay-money span:eq(1)').text(park);
				$('.pay-money span:eq(2)').text(price1);
				$('.pay-money a:eq(0)').attr("data-value",billId);
				$('.pay-money a:eq(0)').data("value",billId);
				$('.pay-money a:eq(0)').attr("data-state",state);
				$('.pay-money a:eq(0)').data("state",state);
			}
		}
		//点击支付按钮触发事件
		function paybill(a){
			//a是元素自身
			var billid = $(a).data("value");
			console.log(billid)
			var bill = new Object();
			bill.id = billid;
			console.log(bill)
			$.ajax({
				url:'user/paybill',
				type:'POST',
				dataType:'json',
				data:
					bill
				,success:function(json){
					console.log(json);
					$('.tbody-add tr').remove();
					var iphone = $('.input-userId').val();
					searchCard(iphone);
				},error:function(){
					
				}
			})
		}
		//关闭支付帐单的弹框
		$('.pay-money a:eq(1)').click(function (){
			$('.pay-money').fadeOut();
		})
		
		
	</script>
</body>

</html>


