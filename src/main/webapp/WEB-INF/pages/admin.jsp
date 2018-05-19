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
				<br />
				<div class="form-gorup">
					<label>*状态:</label> <input type="radio" name="zt" value="可用">可用
					<input type="radio" name="zt" value="不可用">不可用
				</div>
				<button class="btn btn-md btn-primary pull-right" type="button">提交</button>
			</form>
		</div>
		<!-- 添加一个经理的div -->
		<div class="add-block-one add-manger">
			<h3>添加一个经理</h3>
			<br />
			<form>
				<div class="form-gorup">
					<label>*新经理名字</label> <input type="text" class="form-control"
						name="" placeholder="New parking name">
				</div>
				<div class="form-gorup">
					<label>*联系方式</label> <input type="number" class="form-control"
						name="" placeholder="Parking num">
				</div>
				<div class="form-gorup">
					<label>*工资</label> <input type="number" class="form-control"
						name="" placeholder="Parking num">
				</div>
				<br />
				<div class="form-gorup">
					<label>*性别</label> <input type="radio" name="sex" value="男">男
					<input type="radio" name="sex" value="女">女
				</div>
				<button class="btn btn-md btn-primary pull-right" type="button">提交</button>
			</form>
		</div>
		<!-- 添加一个操作员的div -->
		<div class="add-block-one add-operation">
			<h3>添加一个操作员</h3>
			<br />
			<form>
				<div class="form-gorup">
					<label>*新操作员名字</label> <input type="text" class="form-control"
						name="" placeholder="New parking name">
				</div>
				<div class="form-gorup">
					<label>*联系方式</label> <input type="number" class="form-control"
						name="" placeholder="Parking num">
				</div>
				<div class="form-gorup">
					<label>*工资</label> <input type="number" class="form-control"
						name="" placeholder="Parking num">
				</div>
				<br />
				<div class="form-gorup">
					<label>*性别</label> <input type="radio" name="sex" value="男">男
					<input type="radio" name="sex" value="女">女
				</div>
				<button class="btn btn-md btn-primary pull-right">提交</button>
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
		<h1 id='admin-name'>Hello,<span></span></h1>
		<p class="text-info">你的身份是管理员,可以操作以下数据</p>
		<form class="form-inline select-admin">
			<div class="form-group">
				<label>搜索条件</label> <select>
					<option value="名称">名称</option>
					<option value="地址">地址</option>
					<option value="成本">成本</option>
				</select> <input type="text" class="form-control" name="">
			</div>
			<button class="btn btn-defalut">搜索</button>
		</form>
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
				    <img id="close-adminblock" src="img/manger-close.svg">
					<h3>停车场1</h3>
					地址：
					<p></p>
					停车位：
					<p></p>
					成本：
					<p></p>
					状态：
					<p></p>
					<button class="btn btn-md btn-block btn-primary" onclick="editadmin(this)">编辑</button>
				</div>
				<!-- <div class="admin-block-edit" style="display: none;">
                        <h5>停车场1编辑</h4>
                        <div class="form-gourp">
                            <label>名称</label>
                            <input type="text" class="form-control" name="" placeholder="">
                        </div>
                        <div class="form-gourp">
                            <label>地址</label>
                            <input type="text" class="form-control" name="" placeholder="">
                        </div>
                        <div class="form-gourp">
                            <label>停车位数量</label>
                            <input type="text" class="form-control" name="" placeholder="">
                        </div>
                        <div class="form-gourp">
                            <label>成本</label>
                            <input type="text" class="form-control" name="" placeholder="">
                        </div>
                        <br/>
                        <a class="btn btn-primary pull-right">修改</a>
                    </div> -->
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
				    <img id='close-adminblock' src='img/manger-close.svg'>
					<img src="img/admin-tx.svg" id="head-img">
					<p>姓名：</p>
					<p>职位：</p>
					<p>工资：</p>
					<p>电话：</p>
					<p>性别：</p>
					<button class="btn btn-md btn-block btn-primary"
						onclick="editoperation(this)">编辑</button>
				</div>
			</div>
		</div>

	</div>


	<script type="text/javascript">
		// 页面刷新加载停车场信息
		 $(window).ready(function(){
			 var adminname = window.location.href.split("=")[1];
			 console.log(adminname);
			 $('#admin-name span').text(adminname);
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
		 }) 
		 function getparking(num){
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
		     })
		}
		// 停车场对象
		function parking(option) {
			this.parkname = option.name;
			this.parkaddress = option.location;
			this.parknum = option.totalPositionNum;
			this.parkmoney = option.cost;
			this.zt = option.state;
			console.log(this.parkname + this.parkaddress + this.parknum);
		}
		// 检查添加停车场的信息是否不合规
		parking.prototype.check = function() {
			var object = /^[\u4e00-\u9fa5\ \w]{3,15}$/g;
			if (!object.test(this.parkname)) {
				$('.add-parking input:eq(0)').parent().addClass('has-error');
				return 'error';
			} else {
				$('.add-parking input:eq(0)').parent().removeClass('has-error');
			}
			var object = /^[\u4e00-\u9fa5\ \w]{6,25}$/g;
			if (!object.test(this.parkaddress)) {
				$('.add-parking input:eq(1)').parent().addClass('has-error');
				return 'error';
			} else {
				$('.add-parking input:eq(1)').parent().removeClass('has-error');
			}
			if (this.parknum > 10 && this.parknum < 10000) {
				$('.add-parking input:eq(2)').parent().removeClass('has-error');
			} else {
				$('.add-parking input:eq(2)').parent().addClass('has-error');
				return 'error';
			}
			var object = /^[1-9]{1}[0-9]{4,12}.[0-9]{0,3}$/g;
			if (!object.test(this.parkmoney)) {
				$('.add-parking input:eq(3)').parent().addClass('has-error');
				return 'error';
			} else {
				$('.add-parking input:eq(3)').parent().removeClass('has-error');
			}
			return 'ok'
		}
		function closeadminblock(){
			var con = confirm('是否删除停车场');
			if(con){
				
			}else{
				
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
			$('.moudle1')
					.append(
							function() {
								return "<div class='admin-block' >"
										+ "<div>"
										+"<img id='close-adminblock' onClick='closeadminblock()' src='img/manger-close.svg'>"
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
										+ "<button class='btn btn-md btn-block btn-primary' onclick='editadmin(this)'>编辑</button>"
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
		// 点击按钮新建停车场
		$('.add-parking button').click(function() {
			var park = new parking();
			var check = park.check();
			if (check == 'ok') {
				park.parkingadd();
			} else {
				alert('error')
			}

		})

		// 经理对象
		function manger() {
			this.name = $('.add-manger input:eq(0)').val();
			this.phone = $('.add-manger input:eq(1)').val();
			this.money = $('.add-manger input:eq(2)').val();
			this.sex = $('input[name="sex"]:checked').val();
			console.log(this.name + this.phone + this.money + this.sex);
		}

		// 添加新的经理
		manger.prototype.mangeradd = function() {
			console.log(this.parkname + this.parkaddress + this.parknum);
			var name = this.name;
			var phone = this.phone;
			var money = this.money;
			var sex = this.sex;
			$('.moudle2')
					.append(
							function() {
								return "<div class='admin-block manger'>"
										+ "<div>"
										+ "<img src='img/admin-tx.svg' id='head-img'>"
										+ "<p>姓名：<span>"
										+ name
										+ "</span></p>"
										+ "<p>职位：<span>经理</span></p>"
										+ "<p>工资：<span>"
										+ money
										+ "</span></p>"
										+ "<p>电话：<span>"
										+ phone
										+ "</span></p>"
										+ "<p>性别：<span>"
										+ sex
										+ "</span></p>"
										+ "<button class='btn btn-md btn-block btn-primary' onclick='editmanger(this)'>编辑</button>"
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
			var man = new manger();
			man.mangeradd();
		})
		// 编辑停车场
		function editadmin(a) {
			$('.admin-block-edit').remove();
			var parkname = $(a).parent().find('h3').text();
			var parkaddress = $(a).parent().find('span:eq(0)').text();
			var parknum = $(a).parent().find('span:eq(1)').text();
			var parkmoney = $(a).parent().find('span:eq(2)').text();
			$(a)
					.parent()
					.parent()
					.append(
							function() {
								return "<div class='admin-block-edit'>"
										+ "<h5>"
										+ parkname
										+ "编辑</h4>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>名称</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder='' value='"+parkname+"'>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>地址</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder='' value='"+parkaddress+"'>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>数量</label>"
										+ "<input type='number' class='form-control input-sm' name='' placeholder='' value='"+parknum+"'>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>成本</label>"
										+ "<input type='number' class='form-control input-sm' name='' placeholder='' value='"+parkmoney+"'>"
										+ "</div>"
										+ "<div class='form-gorup'>"
										+ "<label>*状态:</label>"
										+ "<input type='radio' name='zt' value='可用'>可用"
										+ "<input type='radio' name='zt' value='不可用'>不可用"
										+ "</div>"
										+ "<a class='btn btn-sm btn-defalut pull-right' onclick='closeparkedit(this)'><img src='img/manger-close.svg'></a>"
										+ "<a class='btn btn-sm btn-primary pull-right'>修改</a>"
								"</div>"
							});
			$('.admin-block-edit').css("opacity", "0");
			$('.admin-block-edit').animate({
				"opacity" : "1"
			}, 600);
		}
		function closeparkedit(a) {
			$('.admin-block-edit').remove();
		}

		// 编辑经理
		function editmanger(a) {
			$('.admin-block-edit').remove();
			var name = $(a).parent().find('h3').text();
			var money = $(a).parent().find('span:eq(0)').text();
			var phone = $(a).parent().find('span:eq(1)').text();
			var sex = $(a).parent().find('span:eq(2)').text();
			$(a)
					.parent()
					.parent()
					.append(
							function() {
								return "<div class='admin-block-edit'>"
										+ "<h5>经理1编辑</h4>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>姓名</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder=''>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>职位</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder=''>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>电话</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder=''>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>性别</label>"
										+ "<input type='radio' name='sex' value='男'>男"
										+ "<input type='radio' name='sex' value='女'>女"
										+ "</div><br/>"
										+ "<a class='btn btn-sm btn-defalut pull-right' onclick='closeparkedit(this)'><img src='img/manger-close.svg'></a>"
										+ "<a class='btn btn-sm btn-primary pull-right'>修改</a>"
								"</div>"
							});
			$('.admin-block-edit').css("opacity", "0");
			$('.admin-block-edit').animate({
				"opacity" : "1"
			}, 600);
		}
		// 编辑操作员
		function editoperation(a) {
			$('.admin-block-edit').remove();
			$(a)
					.parent()
					.parent()
					.append(
							function() {
								return "<div class='admin-block-edit'>"
										+ "<h5>操作员编辑</h4>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>姓名</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder=''>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>职位</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder=''>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>电话</label>"
										+ "<input type='text' class='form-control input-sm' name='' placeholder=''>"
										+ "</div><br/>"
										+ "<div class='form-gourp form-inline'>"
										+ "<label>性别</label>"
										+ "<input type='radio' name='sex' value='男'>男"
										+ "<input type='radio' name='sex' value='女'>女"
										+ "</div><br/>"
										+ "<a class='btn btn-sm btn-defalut pull-right' onclick='closeparkedit(this)'><img src='img/manger-close.svg'></a>"
										+ "<a class='btn btn-sm btn-primary pull-right'>修改</a>"
								"</div>"
							});
			$('.admin-block-edit').css("opacity", "0");
			$('.admin-block-edit').animate({
				"opacity" : "1"
			}, 600);
		}

		// admin、经理、操作员之间的跳转
		// 跳转的时候改变select的值
		function skip(num) {
			if (num == 0) {
				$('.moudle1').show();
				$('.moudle2,.moudle3').hide();
				$('.select-admin option:eq(0)').val("名称").text("名称");
				$('.select-admin option:eq(1)').val("地址").text("地址");
				$('.select-admin option:eq(2)').val("成本").text("成本");
			} else if (num == 1) {
				$('.moudle2').show();
				$('.moudle1').hide();
				$('.select-admin option:eq(1)').val("性别").text("性别");
				$('.select-admin option:eq(2)').val("电话").text("电话");
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
		$('.addparking:eq(2)').click(
				function() {
					$('.add-block,.add-block2').fadeToggle();
					$('.add-block-one:eq(2)').css("display", "block");
					$('.add-block-one:eq(1),.add-block-one:eq(0)').css(
							"display", "none");
				})
		$('.add-block2').click(function() {
			$('.add-block,.add-block2,.selectall-1').fadeOut();
		})
		
		//批量管理
		$('.selectall').click(function() {
			$('.selectall-1,.add-block2').fadeToggle();
		})
	</script>
</body>

</html>


