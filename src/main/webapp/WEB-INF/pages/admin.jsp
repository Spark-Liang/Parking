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
</style>
<script type="text/javascript">
	
</script>
<body>
	<!-- 添加一个停车场的div -->
	<div class="add-block" style="display: none;">
		<div class="add-block-one add-parking">
			<h3>添加一个停车场</h3>
			<br />
			<form>
				<div class="form-gorup">
					<label>*新停车场名称</label> <input type="text" class="form-control"
						name="" placeholder="New parking name">
				</div>
				<div class="form-gorup">
					<label>*地址</label> <input type="text" class="form-control" name=""
						placeholder="Parking address">
				</div>
				<div class="form-gorup">
					<label>*停车位</label> <input type="number" class="form-control"
						name="" placeholder="Parking num">
				</div>
				<div class="form-gorup">
					<label>*成本</label> <input type="number" class="form-control"
						name="" placeholder="Parking money">
				</div>
				<br/>
				<div class="form-gorup">
					<label>*状态:</label> <input type="radio" name="zt" value="可用">可用
					<input type="radio" name="zt" value="不可用">不可用
				</div>
				<button class="btn btn-md btn-primary" type="button">提交</button>
				<a class="add-block-one-close">关闭</a>
			</form>
		</div>
		<!-- 添加一个经理的div -->
		<div class="add-block-one add-manger">
			<h3>添加一个员工</h3>
			<br />
			<form>
				<div class="form-gorup">
					<label>*新员工名字</label> <input type="text" class="form-control"
						name="" placeholder="New name">
				</div>
				<div class="form-gorup">
					<label>*用户名</label> <input type="number" class="form-control"
						name="" placeholder="username">
				</div>
				<div class="form-gorup">
					<label>*密码</label> <input type="number" class="form-control"
						name="" placeholder="password">
				</div>
				<div class="form-gorup">
					<label>*确认密码</label> <input type="number" class="form-control"
						name="" placeholder="check password">
				</div>
				<div class="form-gorup">
					<label>*职位</label>
					<input type="radio" name="position" value="1">经理
					<input type="radio" name="position" value="2">操作员
				</div>
				<button class="btn btn-md btn-primary pull-left" type="button">提交</button>
				<a class="add-block-one-close">关闭</a>
			</form>
		</div>
	</div>
	<div class="add-block2" style="display: none;"></div>
	<!-- 导航条 -->
	<div class="dh">
		<label>Parking management system</label> <a
			class="btn btn-primary btn-md" href="<%=basePath%>inneruser/logout">退出</a>
	</div>
	<!-- 导航条 -->
	<div class="container container-style">
		<h1 id='admin-name'>Hello,<span>XXX</span></h1>
		<p class="text-info">你的身份是管理员,可以操作以下数据</p>
		<ol class="breadcrumb">
			<li><a onclick="skip(0)">停车场管理</a></li>
			<li><a onclick="skip(1)">人员管理</a></li>
		</ol>
		<!-- 停车场模块 -->
		<div class="moudle-one moudle1 ">
			<h4>停车场管理</h4>
			<div class="admin-block addparking">
				<img src="img/admin-add.svg">
			</div>
			<div class="admin-block">
				<div>
				    <img id="close-adminblock" src="img/manger-close.svg" onClick='closeadminblock(this,1)' data-value='fdsa'>
					<h3>停车场1</h3>
					地址：
					<p></p>
					停车位：
					<p></p>
					成本：
					<p></p>
					状态：
					<p></p>
				</div>
			</div>
		</div>
		<!-- admin管理工作人员模块 -->
		<div class="moudle-one moudle2" style="display: none;">
			<h4>工作人员管理</h4>
			<div class="admin-block addparking">
				<img src="img/admin-add.svg">
			</div>
			<div class="admin-block opperation">
				<div>
				    <img id='close-adminblock' src='img/manger-close.svg' onClick='closeadminblock(this,2)'>
				    <h3>Johnay</h3>
				    <p>用户名：</p>
					<p>密码：</p>
					<p>职位：</p>
					<button class="btn btn-md btn-block btn-primary"
						onclick="editoperation(this)">编辑</button>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		//页面刷新加载停车场信息
		 $(window).ready(function(){
			 var adminname = window.location.href.split("=")[2];
			 console.log(adminname);
			 $('#admin-name span').text(adminname);
		     getparking();
		     getworking();
		 }) 
		 function getparking(){
		 	$.ajax({
		         url:'parkinglot/list',
		         type:'GET',
		         dataType:'json',
		         data:{

		         },success:function(responce){
		        	 var res=responce.res;
		        	 console.log(res);
		        	 var length = res.length;
		        	 for (var i = 0;i<length;i++){
			           	var tmpParking=new parking(res[i]);
			           	tmpParking.parkingadd();
		        		
		        	 }
		         },error:function(){

		         }
		     });
		 }

		 function getworking(){
		 	$.ajax({
		         url:'inneruser/selectInnerUser',
		         type:'GET',
		         dataType:'json',
		         data:{

		         },success:function(responce){
		        	 console.log(responce);
	        		 var data =responce.msg;
		        	 var l = data.length;
		        	 for (var i = 0;i<l;i++){
		        		 var work = new manger(1,data[i]);
		        		 work.mangeradd();
		        	 }
		        	 
		         },error:function(){

		         }
		     });
		 }
		//页面刷新加载停车场信息


		// 停车场对象
		function parking(option) {
				this.parkname = option.name;
				this.parkaddress = option.location;
				this.parknum = option.totalPositionNum;
				this.parkmoney = option.cost;
				this.zt = option.state;
				this.parkid = option.id;
			console.log(this.parkname + this.parkaddress +" f"+ this.parknum);
		}
		// 检查添加停车场的信息是否不合规
		function check(a) {
			var object = /^[\u4e00-\u9fa5\ \w]{3,15}$/g;var num = 0;
			if (!object.test(a[0])) {
				$('.add-parking input:eq(0)').parent().addClass('has-error');num++;
			} else {
				$('.add-parking input:eq(0)').parent().removeClass('has-error');num--;
			}
			var object = /^[\u4e00-\u9fa5\ \w]{6,25}$/g;
			if (!object.test(a[1])) {
				$('.add-parking input:eq(1)').parent().addClass('has-error');num++;
			} else {
				$('.add-parking input:eq(1)').parent().removeClass('has-error');num--;
			}
			if (a[2] > 10 && a[2] < 10000) {
				$('.add-parking input:eq(2)').parent().removeClass('has-error');num--;
			} else {
				$('.add-parking input:eq(2)').parent().addClass('has-error');num++;
			}
			var object = /^[1-9]{1}[0-9]{4,12}.[0-9]{0,3}$/g;
			if (!object.test(a[3])) {
				$('.add-parking input:eq(3)').parent().addClass('has-error');num++;
			} else {
				$('.add-parking input:eq(3)').parent().removeClass('has-error');num--;
			}
			if (num>0){
				return 'error';
			}else{
				return 'ok';
			}
			
		}



		//删除停车场或者员工
		function closeadminblock(a,num){
			if(num==1){
				var con = confirm('是否删除停车场');
				if(con){
					alert('yes');
					var id = $(a).data("value");
					console.log(id);
					$.ajax({
						url:'parkinglot/delete',
						type:'GET',
						dataType:'json',
						data:{
							'id':id
						},success:function(data){
					     $(a).parent().parent().remove();
						},error:function(){
							console.log('error');
						}
					})
				}else{
					alert('no')
				}
			}else if(num == 2){
				var con = confirm('是否删除工作人员');
				if(con){
					alert('yes');
					var nickname = $(a).data("value");
					alert(nickname);
					$.ajax({
						url:'inneruser/deleteInnerUser',
						type:'GET',
						dataType:'json',
						data:{
							'nickname':nickname
						},
						success:function(msg){
							console.log(msg);
							$(a).parent().parent().remove();
						},error:function(){
							alert('error')
						}
					})
				}else{
					alert('no')
				}
			}
		}
		//添加成功之后把停车场添加到页面中
		parking.prototype.parkingadd = function() {
			console.log(this.parkname + this.parkaddress + this.parknum);
			var parkname = this.parkname;
			var parkaddress = this.parkaddress;
			var parknum = this.parknum;
			var parkmoney = this.parkmoney;
			var zt = this.zt;
			var parkid = this.parkid;
			$('.moudle1')
					.append(
							function() {
								return "<div class='admin-block' >"
										+ "<div>"
										+"<img id='close-adminblock' onClick='closeadminblock(this,1)' src='img/manger-close.svg' data-value='"+parkid+"'>"
										+ "<h3>"
										+ parkname
										+ "</h3>"
										+ "<p>地址：<span>"
										+ parkaddress
										+ "</span></p>"
										+ "<p>停车位：<span>"
										+ parknum
										+ "</span></p>"
										+ "<p>成本：<span>"
										+ parkmoney
										+ "</span></p>"
										+ "<p>状态：<span>"
										+ zt
										+ "</span></p>"
										// + "<button class='btn btn-md btn-block btn-primary' onclick='editadmin(this)'>编辑</button>"
										+ "</div>" + "</div>"
							})
		}
		// 点击按钮新建停车场
		$('.add-parking button').click(function() {
			var parkinginf = new Array();
			parkinginf[0] = $('.add-parking input:eq(0)').val();//名称
			parkinginf[1] = $('.add-parking input:eq(1)').val();//地址
			parkinginf[2] = $('.add-parking input:eq(2)').val();//车位
			parkinginf[3] = $('.add-parking input:eq(3)').val();//成本
			// parkinginf[4] = $('input[name="zt"]:checked').val();//
			var check1 = check(parkinginf);
			alert(check1);
			if(check1 == 'ok'){
				$.ajax({
				url:'parkinglot/add',
				dataType:'json',
				type:'GET',
				data:{
					'name':parkinginf[0],
					'location':parkinginf[1],
					'totalPositionNum':parkinginf[2],
					'cost':parkinginf[3],
				},success:function(res){
					console.log(res);
					var data = res.parkingLot;
					var tmpParking=new parking(data);
		           	tmpParking.parkingadd();
					
				},error:function(){

				}
			})
			}else if(check1 == 'error'){
				alert('格式错误！！添加失败');
			}
			
			// var check = park.check();
			// if (check == 'ok') {
			// 	var con = confirm('确认添加停车场');
			// 	if(con){
			// 		alert('yes');
			// 		park.parkingadd();
			// 	}else{
			// 		alert('no')
			// 	}
			// } else {
			// 	alert('error');
			// }

		})

		//点击按钮删除停车场


		//操作停车场



		// 经理对象
		function manger(num,manger) {
			if (num == 1){
				this.name = manger.name;
				this.username = manger.nickname;
				this.idd = manger.id;
				if(manger.typeflag==1){
					//经理
					this.working = "经理";
				}else if(manger.typeflag==2){
					//操作员
					this.working = "操作员";
				}
				
			// this.name = $('.add-manger input:eq(0)').val();
			// this.money = $('.add-manger input:eq(1)').val();
			// this.sex = $('input[name="sex"]:checked').val();
			// this.position = $('input[name="position"]:checked').val();
			console.log(this.name + this.username + this.working + this.idd);
			}
			else if(num == 2){
				this.name = manger[0];
				this.username = manger[1];
				if(manger[3]==1){
					this.working = "经理";
				}else if(manger[3]==2){
					this.working = "操作员";
				}
			}
		}

		// 添加新的经理
		manger.prototype.mangeradd = function() {
			var name = this.name;
			var username = this.username;
			var working = this.working;
			var idd = this.idd;
			$('.moudle2')
					.append(
							function() {
								return "<div class='admin-block manger'>"
										+ "<div>"
										+ "<img id='close-adminblock' src='img/manger-close.svg' onClick='closeadminblock(this,2)' data-value='"+username+ "' > "
										+ "<h3>"+name+ "</h3>"
										+ "<p>职位：<span>"+working+"</span></p>"
										+ "<p>用户名：<span>"+username+ "</span></p>"	
										+ "<button class='btn btn-md btn-block btn-primary' onclick='editmanger(this)' data-idd='"+ idd +"'>编辑</button>"
										+ "</div>" + "</div>"
							})
			// $.ajax({
			//     url:'parkinglot/add',
			//     type:'GET',
			//     dataType:'json',
			//     data:{
			//         'totalPositionNum':this.parknum,
			//         'currentPrice': '',
			//         'name':this.parkname,
			//         'location':this.parkaddress,
			//         'cost':this.parkmoney
			//     },success:function(res){
			//         $('.moudle1').append(function(){
			//     return "<div class='admin-block' >"
			//     +"<div>"
			//         +"<h3>"+parkname+"</h3>"
			//         +"<p>地址："+parkaddress+"</p>"
			//         +"<p>停车位："+parknum+"</p>"
			//         +"<p>成本："+parkmoney+"</p>"
			//         +"<p>状态："+parkname+"</p>"
			//         +"<button class='btn btn-md btn-block btn-primary' onclick='editadmin(this)'>编辑</button>"
			//     +"</div>"
			//     +"</div>"
			// })
			//         })
			//     }，error:function(){

			//     }
			// });
		}

		// 点击按钮添加经理 
		$('.add-manger button').click(function() {
			var mangerinf = new Array();
			mangerinf[0] = $('.add-manger input:eq(0)').val();//姓名
			mangerinf[1] = $('.add-manger input:eq(1)').val();//用户名
			mangerinf[2] = $('.add-manger input:eq(1)').val();//密码
			mangerinf[3] = $('input[name="position"]:checked').val();//职位
			alert(mangerinf);
			$.ajax({
				url:'inneruser/addInnerUser',
				type:'POST',
				dataType:'json',
				data:{
					'nickname':mangerinf[1],
					'name':mangerinf[0],
					'password':mangerinf[2],
					'typeflag':mangerinf[3]
				},success:function(msg){
					alert('添加成功');
					console.log(msg);
					var working = new manger(2,mangerinf);
					working.mangeradd();
				},error:function(){
					alert('ff')
				}
			})
			// var man = new manger();
			// man.mangeradd();
		})

		//关闭修改框
		function closeparkedit(a) {
			$('.admin-block-edit').remove();
		}

		//修改数据
		function editworking(a){
			var username = $(a).parent().find('input:eq(0)').val();
			var password = $(a).parent().find('input:eq(1)').val();
			var password2 = $(a).parent().find('input:eq(2)').val();
			var working = $(a).parent().find('input[name="position"]:checked').val();
			var idd = $(a).data("idd");
			var num = 0;
			if (username.length!=11){
				 $(a).parent().find('input:eq(0)').parent().addClass('has-error');num++;
			}else{
				$(a).parent().find('input:eq(0)').parent().removeClass('has-error');num--;
			}
			if(password!=''&&password2!=''){
				if (password != password2){
				 $(a).parent().find('input:eq(2)').parent().addClass('has-error');num++;
				 alert('两次密码不一样！！')
				}else{
					$(a).parent().find('input:eq(2)').parent().removeClass('has-error');num--;
				}num--;
			}else{
				alert('密码不能为空');num++;
				$(a).parent().find('input:eq(2)').parent().addClass('has-error');
			}
			if(working == null){
				num++;
				alert('职位不能为空')
			}else{
				num--;
			}
			
			if(num>=0){
				alert("error");
			}else{
				$.ajax({
				url:'inneruser/changeInnerUser',
				type:'POST',
				dataType:'json',
				data:{
					'id':idd,
					'nickname':username,
					'password':password,
					'typeflag':working,
				},success:function(msg){
					console.log(msg);
					alert('ok');
				},error:function(){

				}
			})
			}
			
		}

		// 编辑经理
		function editmanger(a) {
			$('.admin-block-edit').remove();
			var username = $(a).parent().find('span:eq(1)').text();
			var idd = $(a).data("idd");
			$(a)
					.parent()
					.parent()
					.append(
							function() {
								return "<div class='admin-block-edit'>"
										+ "<h5>员工编辑</h4>"
										+ "<div class='form-gourp'>"
										+ "<label>用户名</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder='' value='"+username+"'>"
										+ "</div>"
										+ "<div class='form-gourp'>"
										+ "<label>新密码</label>"
										+ "<input type='password' class='form-control input-sm' name='' placeholder=''>"
										+ "</div>"
										+ "<div class='form-gourp'>"
										+ "<label>重新输入密码</label>"
										+ "<input type='password' class='form-control input-sm' name='' placeholder=''>"
										+ "</div>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>职位</label>"
										+ "<input type='radio' name='position' value='1'>经理"
										+ "<input type='radio' name='position' value='2'>操作员"
										+ "</div>"
										+ "<a class='btn btn-sm btn-defalut pull-right' onclick='closeparkedit(this)'><img src='img/manger-close.svg'></a>"
										+ "<a class='btn btn-sm btn-primary pull-right' onclick='editworking(this)' data-idd='"+idd+"'>修改</a>"
								"</div>"
							});
			$('.admin-block-edit').css("opacity", "0");
			$('.admin-block-edit').animate({
				"opacity" : "1"
			}, 600);
		}
		//经理的操作


		// admin、经理、操作员之间的跳转
		function skip(num) {
			if (num == 0) {
				$('.moudle1').show();
				$('.moudle2').hide();

			} else if (num == 1) {
				$('.moudle2').show();
				$('.moudle1').hide();
				//获取经工作人员的信息
				// $.ajax({
				// 	url:'',
				// 	type:'GET',
				// 	dataType:'json',
				// 	data:{

				// 	},success:function(data){

				// 	},error:function(){

				// 	}
				// })
			}
		}


		// 添加
		$('.addparking:eq(0)').click(
				function() {
					$('.add-block,.add-block2').fadeToggle();
					$('.add-block-one:eq(0)').css("display", "block");
					$('.add-block-one:eq(1),.add-block-one:eq(2)').css(
							"display", "none");
				})
		$('.addparking:eq(1)').click(
				function() {
					$('.add-block,.add-block2').fadeToggle();
					$('.add-block-one:eq(1)').css("display", "block");
					$('.add-block-one:eq(0),.add-block-one:eq(2)').css(
							"display", "none");
				})
		$('.add-block2,.add-block-one-close').click(function() {
			$('.add-block,.add-block2,.selectall-1').fadeOut();
		}) 
	</script>
</body>

</html>


