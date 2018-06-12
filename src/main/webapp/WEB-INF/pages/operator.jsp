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
</style>
<script type="text/javascript">
	
</script>
<body>
	<div class="pay-money alert alert-info">
		<h4>确认支付帐单</h4>
		<p>卡号：<span>gfd</span><p>
		<p>停车场：<span></span><p>
		<h3>支付金额：<span>fds</span></h3>
		<br/>
		<a class="btn btn-block btn-primary" data-value="0" onclick="paybill(this)">支付</a>
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
						<input type="text" class="form-control input-sm" id="exampleInputName2" placeholder="请输入7位卡号">
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
					<button class="btn btn-primary btn-sm pull-right payBill-btn">支付账单</button>
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
									var con = confirm('该用户不存在，是否新增用户？');
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
							alert('成功新增用户')
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
				if(a==1){
					
				}else{
					
				}
			/* 	$.ajax({
					url:'user/stopCard',
					dataType:'json',
					type:'POST',
					data:{
						'cardId':inf[1]
					},success:function(json){
						console.log(json)
					},error:function(){
						
					}
				})
 */			}
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
		*/
		function checkBillData(json){
			
			if(json.list[0].currentBill!=null&&json.list[0].parking==true){
				alert('存在没有支付的账单以及停车卡正在使用');
				payBills();
				return 0;
			}else if(json.list[0].currentBill!=null){
				alert('存在没有支付的账单');
				payBills(0);//0是表示有上季度的账单没有支付
				return 0;
			}else if(json.list[0].currentBill==null){
				alert('需要先支付这个季度使用的费用');
				payBills(1);//1表示没有上个月未支付的账单，但是需要支付这个月的账单。
			}else if(json.list[0].parking==true){
				alert('停车卡正在使用');
				return 0;
			}else{
				return 1;
			}
			//payBills函数中传过来的值是用于判断是存在账单还是不存在账单但是需要支付的状况
			//用于显示用户的账单信息
			function payBills(num){
					$('.payBill').show();
				if (num == 0 ){
					var sDate = new Date(json.list[0].currentBill.billStartDate);
					var startDate = sDate.toLocaleString('chinese',{hour12:false});
					var eDate = new Date(json.list[0].currentBill.billEndDate);
					var endDate = eDate.toLocaleString('chinese',{hour12:false});
					$('.BillTable').append(function(){
						return "<tr>"
						+"<td>"+json.list[0].userId+"</td>"
						+"<td>"+startDate+"~"+endDate+"</td>"
						+"<td>"+json.list[0].parkingLot[0].cost+" ￥/每月</td>"
						+"<td>"+json.list[0].currentBill.price+"</td>"
						+"</tr>"
					})
				}else if(num == 1){
					var date = new Date();
					var day = date.getDate();
					var month = date.getMonth()+1;
					var year = date.getFullYear();
					var time1 = date.toLocaleString('chinese',{hour12:false});
					var time = time1.substring(0,9);
					var allDayNum = allDay(month);
					var useDay = useMuchDay(day,month);
					var price = (json.list[0].parkingLot[0].cost * 3)*(useDay/allDayNum[0]);
					var price1 = Math.ceil(price);
					$('.BillTable').append(function(){
						return "<tr>"
						+"<td>"+json.list[0].userId+"</td>"
						+"<td>"+year+"-"+allDayNum[1]+"-1 ~"+time+"</td>"
						+"<td>"+json.list[0].parkingLot[0].cost+" ￥/每月</td>"
						+"<td>"+price1+"</td>"
						+"</tr>"
					})
				}
				function allDay(month){
					var allday = new Array();
					if(month<3){
						allday[0]=90;
						allday[1]=1;
						return allday;
					}else if(3<month<7){
						allday[0]=91;
						allday[1]=4;
						return allday;
					}else if(6<month<10){
						allday[0]=92;
						allday[1]=7;
						return allday;
					}else if(9<month<=12){
						allday[0]=91;
						allday[1]=10;
						return allday;
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
			alert('fff');
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
									billid = json.list[i].currentBill.id;
									price = json.list[i].currentBill.price;
								}else{
									billid = 0;
									price = 0;
								}
								$('.tbody-add').append(function (arr){
									return "<tr>"
									+"<td>"+json.list[i].cardId+"</td>"
									+"<td></td>"
									+"<td>"+json.list[i].parkingLot.name+"</td>"
									+"<td>"+json.list[i].userId+"</td>"
									+"<td>"+json.list[i].state+"</td>"
									+"<td>"
									+"<a class='btn btn-default btn-xs' data-value='"+json.list[i].cardId+"' onclick='updateCard(this)'>更换停车卡</a>"
									+"<a class='btn btn-default btn-xs' onclick='paymoney(this)' data-billid='"+billid+"' data-cardid='"+json.list[i].cardId+"' data-park='"+json.list[i].parkingLot.name+"' data-price='"+price+"'>支付帐单</a>"
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
			}else{
				alert('手机号格式出错');
				$(this).parent().find('input:eq(0)').parent().addClass('has-error');
			}
		})
		
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
		        	alert('停车卡格式有问题！！')
		        	$(a).parent().parent().find('input').parent().addClass('has-error');
		        }  
		}
		//下面两个方法都是操作员搜索停车卡信息模块的
		//点击支付帐单的触发事件 
		function paymoney(a){
			var billId = $(a).data("billid");
			if(billId == 0){
				alert('当前停车卡没有需要支付的账单');
			}
			else{
				var park = $(a).data("park");
				var price = $(a).data("price");
				var cardid = $(a).data("cardid");
				$('.pay-money').fadeIn();
				$('.pay-money span:eq(0)').text(cardid);
				$('.pay-money span:eq(1)').text(park);
				$('.pay-money span:eq(2)').text(price);
				$('.pay-money a:eq(0)').attr("data-value",billId);
			}
		}
		//点击支付按钮触发事件
		function paybill(a){
			//a是元素自身
			var billid = $(a).data("value");
			var bill = new Object();
			bill.id = billid;
			//bill.isPaid = true;
			console.log(bill)
			$.ajax({
				url:'user/paybill',
				type:'POST',
				dataType:'json',
				data:
					bill
				,success:function(json){
					console.log(json);
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


