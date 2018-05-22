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
<meta charset="UTF-8">
<base href="<%=basePath%>">
<title>用户登录</title>
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
body {
	padding: 0;
	margin: 0;
}

.login {
	width: 350px;
	height: 400px;
	margin: 250px auto;
	padding: 0 30px;
	position: relative;
}

#background-img {
	position: fixed;
	width: 100%;
	height: 100%;
	z-index: -1;
	margin-top: -250px;
}

.loginback {
	background-color: black;
	opacity: 0.5;
	position: absolute;
	width: 100%;
	height: 100%;
	margin: -20px 0 0 -30px;
	z-index: -1;
	border-radius: 5px;
}

.login h2 {
	font-weight: 600;
	text-align: center;
	color: white;
}

.login label {
	color: white;
}

.login select {
	display: block;
	width: 80px;
	height: 25px;
	border-radius: 3px;
	border: 1px solid grey;
}

.tip {
	color: red;
	left:;
	top:;
}
</style>
<html>
<body>
	<img id="background-img" src="img/loginback.jpg">
	<div class="login">
		<div class="loginback"></div>
		<h2>
			工作人员登录
			</h3>
			<br />
			<p class="tip"></p>
			<form id="formLogin">
				<div class="form-gorup">
					<label>用户名</label> <input type="text" class="form-control userName"
						name="nickname" placeholder="username" onblur="checkName()">
				</div>
				<br />
				<div class="form-gorup">
					<label>密码</label> <input type="password" class="form-control pass"
						name="password" placeholder="password">
				</div>
				<br /> <label>角色选择</label> <select name="typeflag">
					<option value="0">管理者</option>
					<option value="1">经理</option>
					<option value="2">操作者</option>
				</select>
			</form>

			<br /> <a class="btn btn-md btn-primary pull-right btn-block"
				onclick="login(this)" href="javascript:void(0);">登录
				</a>
	</div>

	<script type="text/javascript">
		function checkName() {
			//检测用账号
			var uName = $('.userName').val().trim();
			var nameLength = uName.length;
			var regex = new RegExp("^[0-9a-zA_Z]+$");
			var tip = document.getElementsByClassName('tip')[0];
			var select = $('.login select').val();
			if (nameLength < 1) {
				tip.innerHTML = "请输入账号";
				$('.userName').focus();
				return;
			} else if (nameLength > 16) {
				tip.innerHTML = '账号长度不能超出16位';
				$('.userName').focus();
				return;
			} else if (uName.indexOf(' ') !== -1) {
				tip.innerHTML = '账号中间不能有空格';
				$('.pass').focus();
				return;
			} else if (!regex.test(uName)) {
				tip.innerHTML = "账号只能包含英文和数字";
				$('.userName').focus();
				return;
			}
		}

		function login(a) {
			//用户名和密码格式以及非空验证
			var uName = $('.userName').val().trim();
			var nameLength = uName.length;
			var regex = new RegExp("^[0-9a-zA-Z]+$");
			var tip = document.getElementsByClassName('tip')[0];
			var select = $('.login select').val();
			if (nameLength < 1) {
				tip.innerHTML = "请输入账号";
				$('.userName').focus();
				return;
			} else if (nameLength > 16) {
				tip.innerHTML = '账号长度不能超出16位';
				$('.userName').focus();
				return;
			} else if (!regex.test(uName)) {
				tip.innerHTML = "账号只能包含英文和数字";
				$('.userName').focus();
				return;
			} else if ($('.pass').val().trim().length < 1) {
				tip.innerHTML = "请输入密码";
				$('.pass').focus();
				return;
			} else if ($('.pass').val().trim().indexOf(' ') !== -1) {
				tip.innerHTML = '密码中间不能有空格';
				$('.pass').focus();
				return;
			}
			//ajax提交表单
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "inneruser/login", //提交的地址  
				data : $('#formLogin').serialize(),
				error : function(request) {
					$('.tip').text('登录请求提交出错');
					$('.userName').val('');
					$('.pass').val('');
					return;
					//alert("Connection error");  
				},
				success : function(user) {
					if (user === null) {
						$('.tip').text('请检查用户名、密码和角色');
						$('.userName').val('');
						$('.pass').val('');
						return;
					} else {
						
						if (select == 0) {
							var hre = "<%=basePath%>parkinglot/admin?id=" + user.nickname + "&name=" +user.name;

						} else if (select == 1) {
							var hre = "<%=basePath%>parkinglot/manager?id=" + user.nickname + "&name=" +user.name;

						} else if (select == 2) {

						}
						$(location).attr('href', hre);
					}
				}
			});

			/*var username = $('.login input:eq(0)').val();
			var password = $('.login input:eq(1)').val();
			console.log(password);
			var obj = /^\d{8,14}$/g;
			// alert(obj.test(password.value))
			if (obj.test(password)) {
			    console.log('ok');
			}
			else{
			    console.log('error');
			}
			var select = $('.login select').val();
			console.log(select)
			if(select == 0){
			    var hre = "admin.html?id";
			    $(a).attr("href",hre);
			}else if(select == 1){
			    var hre = "manger.html?id";
			    $(a).attr("href",hre);
			}else if(select == 2){

			}*/
		}
	</script>
</body>
</html>