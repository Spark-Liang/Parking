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
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    
</head>

<style type="text/css">
body{
    padding: 0;
    margin: 0;
}
    .login{
        width: 350px;
        height: 400px;
        margin: 250px auto;
        padding: 0 30px;
        position: relative;
    }
    #background-img{
        position: fixed;
        width: 100%;
        height: 100%;
        z-index: -1;
        margin-top: -250px;
    }
    .loginback{
        background-color: black;
        opacity: 0.5;
        position: absolute;
        width: 100%;
        height: 100%;
        margin: -20px 0 0 -30px;
        z-index: -1;
        border-radius: 5px;
    }
    .login h2{
        font-weight: 600;
        text-align: center;
        color: white;
    }
    .login label{
        color: white;
    }
    .login select{
        display: block;
        width: 80px;
        height: 25px;
        border-radius: 3px;
        border: 1px solid grey;
    }
    .tip{
        color: red;
        
        left:;
        top:;
    }
</style>
<html>
<body>
    <img id="background-img" src="img/loginback.jpg">
<div class="login">
    <div class="loginback">
        
    </div>
    <h2>用户登录</h3>
        <br/>
    <p class = "tip"></p>
    <form id = "formLogin">
        <div class="form-gorup">
            <label>用户名</label>
            <input type="text" class="form-control userName" name="" placeholder="手机号">
        </div>
        <br/>
        <div class="form-gorup">
            <label>密码</label>
            <input type="password" class="form-control pass" name="" placeholder="password">
        </div>
        
    </form>
    <br/>
    
    <br/>
    <a class="btn btn-md btn-primary pull-right btn-block"  onclick="login(this)" href="#">登录</button>
</div>

<script type="text/javascript">
    var tip = document.getElementsByClassName('tip')[0];
    var className = "userName";
    var inputName = document.getElementsByClassName('userName')[0];
    inputName.addEventListener("blur",function(){checkName(className,tip);});//监听填写完账号后进行检测
    inputName.addEventListener("change",function(){cleanTip(tip);});//监听修改账号或密码时清空出错提示
    
     function login(a){
        //密码格式以及非空验证
        var pwdClass = "pass";
        var result = checkPwd(pwdClass,tip);
        //检测通过后通过ajax提交表单
        if(result){
            cleanTip(tip);
            $.ajax({
                type : "POST",
                dataType : "json",
                url : "", //提交的地址  
                data : $('#formLogin').serialize(),
                error : function(request) {
                    $('.tip').text('登录请求提交出错');
                    $('.userName').val('');
                    $('.pass').val('');
                    return;
                },
                success : function(user) {
                    if (user === null) {
                        $('.tip').text('账号或密码错误');
                        $('.userName').val('');
                        $('.pass').val('');
                        return;
                    } else {
                        var hre="";
                        $(location).attr('href',hre);
                    }
                }
            });
        }
       
    }

</script>
<script src = "js/check6.js"></script>
</body>

</html>