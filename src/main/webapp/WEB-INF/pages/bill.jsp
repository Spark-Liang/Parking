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
    <title>用户查看帐单页面</title>
    <link rel="stylesheet" href="css/manger.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="laydate/laydate.js"></script> 

</head>

<style type="text/css">
    
</style>
<body>

   
    
    <div class="dh">
        <label>Parking management system</label>
        <a class="btn btn-primary btn-md" href="login.html">退出</a>
    </div>
    <div class="container container-style" >
        <h1>Hello,XXX</h1>
        <p class="text-info">你的身份是普通用户,可以查看你的帐单信息</p>
        <div class="moudle-one moudle1 " >
            <h4>帐单信息查阅</h4>
            <div class="admin-block">
                <br/>
                <p>卡号:<span>1023400</span></p>
                <p>停车场名称：<span>停车场1</span></p>
                <p>日期：<span>2018-1~2018-3</span></p>
                <p>应付款金额:<span>￥500</span></p>
                <p>付款状态：<span>未付款</span></p>
                <button class="btn btn-md btn-block btn-primary">支付帐单</button>
            </div>
        </div>
    </div>
       
<script type="text/javascript">
    $(document).ready(function(){
        $.ajax({
            type: "GET",
            dateType: "json",
            url:"",
            data:{

            },success:function(responce){
                var res=responce.res;
                console.log(res);
                var length = res.length;
                for (var i = 0;i<length;i++){
                    var tmpBill = new bill(res[i]);
                    tmpBill.billadd();
                        
                }
            },error:function(){

            }
        });
    });

    function bill(option) {
        this.parkname = option.name;
        this.cardnum = option.cardnum;
        this.month = option.month;
        this.money = option.money;
        this.status = option.status;
    }

    //添加成功之后把停车场添加到页面中
    bill.prototype.billadd = function() {
        var parkname = this.parkname;
        var cardnum = this.cardnum;
        var date = this.date;
        var money = this.money;
        var status = this.status;
        $('.moudle1').append(function() {
            return "<div class='admin-block' >"
                    + "<div>"
                    + "<br/>"
                    + "<p>卡号：<span>"
                    + cardnum
                    + "</span></p>"
                    + "<p>停车场名称：<span>"
                    + parkname
                    + "</span></p>"
                    + "<p>日期：<span>"
                    + date
                    + "</span></p>"
                    + "<p>应付款金额：￥<span>"
                    + money
                    + "</span></p>"
                    + "<p>付款状态：<span>"
                    + status
                    + "</span></p>"
                    + "</div>" + "</div>";
        });
    }
/*for(var i = 0; i <= 10; i++){
    $('.moudle1').append(function() {
            return "<div class='admin-block' >"
                    + "<div>"
                    + "<br/>"
                    + "<p>卡号：<span>"
                    + "cardnum"
                    + "</span></p>"
                    + "<p>停车场名称：<span>"
                    + "parkname"
                    + "</span></p>"
                    + "<p>日期：<span>"
                    + "date"
                    + "</span></p>"
                    + "<p>应付款金额：￥<span>"
                    + "money"
                    + "</span></p>"
                    + "<p>付款状态：<span class = 'status'>"
                    + "已付款"
                    + "</span></p>"
                    //+'<button class="btn btn-md btn-block btn-primary">支付帐单</button>'
                    + "</div>" + "</div>";
    });
}*/
    

    
    
</script>
</body>

</html>