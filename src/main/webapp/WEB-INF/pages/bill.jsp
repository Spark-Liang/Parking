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
    <title>用户查看帐单页面</title>
    <link rel="stylesheet" href="css/manger.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    

</head>

<style type="text/css">
    .admin-block{
        height: 200px;
    }
    .showBill{
        position: fixed;
        width: 100%;
        height: 100%;
        background-color: white;
        z-index: 1000;
        display: none;
    }
    .bill{
        width: 620px;
        height: 70%;
        margin: 4% auto;
    }
    .mystyle{
        font-size: 120%;
        
    }
    .showBill_1{
        border-bottom: 1px solid black;
    }
    /*table{
        text-align: center;
    }
    .table-condensed>thead>tr>th {
        padding: 5px 20px;
        text-align: center;
    }*/
    .showBill a{
        text-decoration: none;
        color: #337ab7;
        cursor: pointer;
    }
    /*table样式*/
    .tableStyle{
        margin: 100px auto;
        border-collapse:collapse;
        text-align: center;

    }
    .tableStyle tr{
        border-top: 1px grey solid;
    }
    .tableStyle td{

        padding: 5px 20px;
        
    }
    .tableStyle tr:nth-of-type(1){
        border-top-color: black
    }
    .tableStyle th{
        padding: 5px 20px;
        text-align: center;
    }
    .nooutbill table{
        margin:100px 0;
    }
</style>
<body>

   
    <!--点击查看帐单按钮显示帐单内容-->
    <div class = "showBill">
        <div class = "bill">
            <!--账单关闭按钮-->
            <div id="usageClose" onclick = closeBill()>
                <img src="img/manger-close2.svg"  style = "cursor: pointer">
                              <strong>关闭</strong>
            </div>
            <div class = showBill_1>
                <br/>
                <p class = "billMenu"><a ><span class = "mystyle">查看已出帐单</span></a>&nbsp<span style="font-size: 200%">|</span>&nbsp<a ><span>查看未出帐单</span></a></p>

            </div>
            <!--已出账单详细信息-->
            <div class = "outbill">
                <table class="tableStyle">
                    <thead>
                    <tr>
                        <th>帐单编号</th>
                        <th>日期</th>
                        <th>最后缴费日期</th>
                        <th>应付金额(RMB)</th>
                        <th>付款状态</th>
                        
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>123</td>
                        <td>2018.1.5-2018.3.5</td>
                        <td>2018.4.5</td>
                        <td>500</td>
                        <td>已付款</td>
                    </tr>
                    <tr>
                        <td>123</td>
                        <td>2018.1.5-2018.3.5</td>
                        <td>2018.4.5</td>
                        <td>500</td>
                        <td>已付款</td>
                    </tr>
                    <tr>
                        <td>123</td>
                        <td>2018.1.5-2018.3.5</td>
                        <td>2018.4.5</td>
                        <td>500</td>
                        <td>已付款</td>
                    </tr>
                    </tbody>
                </table> 
            </div>
            <!--未出账单详细信息-->
            <div class = "nooutbill" style="display: none">
                <table class="tableStyle">
                <thead>
                    <tr>
                        <th>帐单编号</th>
                        <th>日期</th>
                        <th>预计下季度付款金额(RMB)</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>123</td>
                        <td>2018.1.5-2018.3.5</td>
                        <td>500</td>
                    </tr>
                    <tr>
                        <td>1551</td>
                        <td>2018.1.5-2018.3.5</td>
                        <td>500</td>
                        
                    </tr>
                    <tr>
                        <td>1df3</td>
                        <td>2018.1.5-2018.3.5</td>
                        <td>500</td>
                        
                    </tr>
                </tbody>
            </table> 
            </div>

        </div>
        
    </div> 

    <!--停车卡页面-->
    <div class="dh">
        <label>Parking management system</label>
        <a class="btn btn-primary btn-md" href="login.html">退出</a>
    </div>
    <div class="container container-style" >
        <h1>Hello,<span>XXX</span></h1>
        <br/>
        <div class="moudle-one moudle1 " >
            <h4>拥有的停车卡</h4>
            <div class="admin-block">
                <br/>
                <p>卡号:<span>1023400</span></p>
                <p>开卡日期：<span>2018.1.1</span></p>
                <p>
                <!--<p>卡状态：正常使用</p>-->
                <button class="btn btn-md btn-block btn-primary" onclick = 'showBill()'>查看帐单</button>
            </div>
        </div>
    </div>
    
    <!--点击查看帐单按钮显示帐单内容-->
    
<script type="text/javascript">
    /*$(document).ready(function(){
        $.ajax({
            type: "GET",
            dateType: "json",
            url:"",
            date{

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
    });*/
    //将ajax请求回来的停车卡数据保存

    function card(option){
        this.cardnum = option.cardnum;
        this.date = option.date;
        if(option.status == "1"){
           
        }else{
            this.remind = "";
        }
       
        //console.log(this.cardnum + this.date + this.remind);
    }

    //测试
    /*var option = {
        cardnum: '123',
        date: '2018',
        status: '1'
    };
    console.log(option.cardnum + option.date +option.status);
    var option2 = {
        cardnum: 'abc',
        date: '2018',
        status: '0'
    };
    var card1 =new card(option);
    console.log(card1.cardnum + card1.date + card1.remind);
    var card1 =new card(option2);
    console.log(card1.cardnum + card1.date + card1.remind);
    var card2 =new card(option2);
    console.log(card2.cardnum + card2.date + card2.remind);
    */

    //添加成功之后把停车卡添加到页面中
    card.prototype.cardadd = function() {
        var parkname = this.parkname;
        var cardnum = this.cardnum;
        var date = this.date;
        var status = this.status;
        $('.moudle1').append(function() {
            return "<div class='admin-block' >"
                    + "<div>"
                    + "<br/>"
                    + "<p>卡号：<span>"
                    + cardnum
                    + "</span></p>"
                    + "<p>开卡日期：<span>"
                    + date
                    + "</span></p>"
                    +'<button class="btn btn-md btn-block btn-primary">查看帐单</button>'
                    + "</div>" + "</div>";
        });
    }
    for(var i = 0; i <= 6; i++){
        $('.moudle1').append(function() {
            return "<div class='admin-block' >"
                    + "<div>"
                    + "<br/>"
                    + "<p>卡号：<span>"
                    + "cardnum"
                    + "</span></p>"
                    + "<p>开卡日期：<span>"
                    + "date"
                    + "</span></p>"
                    +'<button class="btn btn-md btn-block btn-primary">查看帐单</button>'
                    + "</div>" + "</div>";
        });
    }

    //点击检查帐单弹出帐单页面
    function showBill(){
        console.log(this);//保存卡号，用来请求账单信息
        span1.classList.add("mystyle");
        span2.classList.remove("mystyle");
        var showBill = document.getElementsByClassName("showBill")[0];
        showBill.style.display = 'block';
        outbill.style.display = "block";
        nooutbill.style.display = "none";

    }
    //点击关闭，关闭帐单页面
    function closeBill(){
        var showBill = document.getElementsByClassName("showBill")[0];
        showBill.style.display = 'none';
    }

    //帐单导航样式变化
    var billMenu = document.getElementsByClassName("billMenu")[0];
    console.log(billMenu);
    var span1 = billMenu.getElementsByTagName("span")[0];
    var span2 = billMenu.getElementsByTagName("span")[2];
    //console.log(span1);
    //console.log(span2);
    span1.addEventListener('click',menuChange1);//监听点击查看已出账单
    span2.addEventListener('click',menuChange2);//监听点击查看未出账单
    var outbill = document.getElementsByClassName("outbill")[0];
    var nooutbill = document.getElementsByClassName("nooutbill")[0];
    
    //点击查看已出账单触发的函数
    function menuChange1(){
        span1.classList.add("mystyle");
        span2.classList.remove("mystyle");
        outbill.style.display = "block";
        nooutbill.style.display = "none";
    }

    //点击查看未出账单触发的函数
    function menuChange2(){
        span2.classList.add("mystyle");
        span1.classList.remove("mystyle");
        outbill.style.display = "none";
        nooutbill.style.display = "block";
    }

    //页面加载后获取停车卡信息
    /*$(document).ready(function(){
        $.ajax({
            url:'',
            type:'GET',
            dateType:'json',
            data{

            },
            success: function(responce){
                var data =responce.msg;
                var length = data.length;
                for (var i = 0; i<length; i++){
                var card1 = new card(data[i]);
                    card1.cardadd();
                }
            },
            error: function(){

            }
        });
    });*/
    
    //点击查看已出账单发送ajax请求获取账单信息
    /*var card = document.getElementsByClassName
    function(){
        $.ajax({
            url:'',
            type: 'GET',
            dataType:'json',
            data{
                cardId: cardId;
            },
            success: function(){

            },
            error: function(){

            }
        });
    }*/
</script>
</body>

</html>