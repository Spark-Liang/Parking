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

        padding: 5px 18px;
        
    }
    .tableStyle tr:nth-of-type(1){
        border-top-color: black
    }
    .tableStyle th{
        padding: 5px 18px;
        text-align: center;
    }
    .nooutbill table{
        margin:100px 0;
    }
    .nooutbill th{
        padding: 5px 60px;
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
                    <tbody id = "outBillData">
                    <!-- 写死的静态数据 -->
                    <!--
                    <tr>
                        <td>1</td>
                        <td>2017.1.1-2017.3.31</td>
                        <td>2017.4.30</td>
                        <td>600</td>
                        <td>已付款</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>2017.4.1-2017.6.30</td>
                        <td>2017.7.31</td>
                        <td>600</td>
                        <td>已付款</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>2017.7.1-2017.7.31<br>2017.9.1-2017.9.30</td>
                        <td>2017.10.31</td>
                        <td>400</td>
                        <td>未付款</td>
                    </tr>
                     -->
                    </tbody>
                </table> 
            </div>
            <!--未出账单详细信息-->
            <div class = "nooutbill" style="display: none">
                <table class="tableStyle">
                <thead>
                    <tr>
                        
                        <th>日期</th>
                        <th>预计下季度付款金额(RMB)</th>
                    </tr>
                </thead>
                <tbody id = "noOutBillData">
                    <tr>                  
                        <td>2018.4.1-2018.6.30</td>
                        <td>250</td>
                    </tr>
                </tbody>
            </table> 
            </div>

        </div>
        
    </div> 

    <!--停车卡页面-->
    <div class="dh">
        <label>Parking management system</label>
        <a class="btn btn-primary btn-md" href="<%=basePath%>user/userLogout">退出</a>
    </div>
    <div class="container container-style" >
        <h1>Hello,<span>XXX</span></h1>
        <br/>
        <div class="moudle-one moudle1 " >
            <h4>拥有的停车卡</h4>
            
        </div>
    </div>
    
    <!--点击查看帐单按钮显示帐单内容-->
    
<script type="text/javascript">

    //点击检查帐单弹出帐单页面
    function showBill(a){
        console.log(a);//保存卡号，用来请求账单信息
        var accountId = $(a).data('accountid');
        console.log("accountId:"+accountId);
        span1.classList.add("mystyle");
        span2.classList.remove("mystyle");
        var showBill = document.getElementsByClassName("showBill")[0];
        showBill.style.display = 'block';
        outbill.style.display = "block";
        nooutbill.style.display = "none";
        
		//ajax发送账户ID给后台拿账单数据
		var userId = GetUrlParam("id");
		$.ajax({
			url: "user/checkBillInfo",
			type: 'GET',
			data:{
				userId: userId,
				accountId: accountId
			},
			dataType:'json',
			success: function(respon){
				//先把旧数据删除
				$("tbody").find("tr").remove();
				console.log(respon);
				console.log(respon.msg);
				var length = respon.msg.length;
				for(var i = length-1; i >= 0; i--){
					var id = respon.msg[i].id;//获取账单编号id
					//日期通过getDate函数转换格式
					var startTime = getDate(respon.msg[i].timeQuantums[0].startTime);
					var endTime = getDate(respon.msg[i].timeQuantums[0].endTime);
					var lastPayDate = getDate(respon.msg[i].lastPayDate);//获取最后缴费日期
					//console.log(lastPayDate);
					var price = respon.msg[i].price;//获取应付金额
					//console.log("应付金额为："+price);
					
					//根据paid的布尔值来判断账单是否已经付款
					var payState = "已付款";
					if(respon.msg[i].paid === false){
						payState = "未付款";
					}
					var flag = respon.msg[i].timeQuantums.length;//获取日期长度用来判断账单是否有断开日期
					if(flag === 1 ){
						var startTime = getDate(respon.msg[i].timeQuantums[0].startTime);
						var endTime = getDate(respon.msg[i].timeQuantums[0].endTime);
						var time = startTime + "-"+endTime;
						//已出账单详细数据信息添加到表格
						$('#outBillData').append(function(){
							return "<tr>"
							+"<td>"+id+"</td>"
							+"<td>"+time+"</td>"
							+"<td>"+lastPayDate+"</td>"
							+"<td>"+price+"</td>"
							+"<td>"+payState+"</td></tr>"
						});
						//console.log("time为："+time);
					}else if(flag === 2){
						var startTime = getDate(respon.msg[i].timeQuantums[0].startTime);
						var endTime = getDate(respon.msg[i].timeQuantums[0].endTime);
						var time = startTime + "-"+endTime;
						var startTime2 = getDate(respon.msg[i].timeQuantums[1].startTime);
						var endTime2 = getDate(respon.msg[i].timeQuantums[1].endTime);
						var time2 = startTime2 + "-"+endTime2;
						$('#outBillData').append(function(){
							return "<tr>"
							+"<td>"+id+"</td>"
							+"<td>"+time+"<br>"+time2+"</td>"
							+"<td>"+lastPayDate+"</td>"
							+"<td>"+price+"</td>"
							+"<td>"+payState+"</td></tr>"
						});
					}					
				}
				
			},
			error: function(){
				alert("数据请求发送失败");
			}
		});
		
    }

    //点击关闭，关闭帐单页面
    function closeBill(){
        var showBill = document.getElementsByClassName("showBill")[0];
        showBill.style.display = 'none';
    }

    //查看已出账单和未出账单选项的样式变化
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

    //获取参数方法
	function GetUrlParam(name){
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
    
    //日期处理方法
    function getDate(date){
    	var mydate = new Date(date);
    	var myMonth = mydate.getMonth()+1;
    	var myYear = mydate.getFullYear();
    	var myDay = mydate.getDate();
    	return myYear + "." + myMonth + "." + myDay;
    } 
    
    //页面加载后获取停车卡信息
    $(document).ready(function(){
    	
    	//调用方法获取url中的id参数
    	var userId = GetUrlParam("id");
    	//alert(userId);   
    	 $.ajax({
             url:'user/getAllAccount',
             type:'POST',
             dataType:'json',
             data:{
				userId: userId,
				isGetAll: 'true'
             },success: function(json){
            	 console.log(json);
            	 var l = json.list.length;
            	 for(var i = 0; i < l; i++){
            		 var tip = "有未付款账单";
            		 console.log(json.list[i].currentBill.paid);
            		 if(json.list[i].currentBill.paid === true){
            			 tip = "";
            		 }
            		 var cardNum = json.list[i].cardId;
                	 var startDate = getDate(json.list[i].stateStartDate);//转换日期格式
                	 var accountId = json.list[i].currentBill.accountId;
                	 var userId = json.list[i].userId;
                	 //加载停车卡
                	 $('.moudle1').append(function(){
                         return "<div class='admin-block'>"
                         +"<p>卡号："+cardNum+"</p>"
                         +"<p>开卡日期："+startDate+"</p>"
                         +"<p style = 'color: red'>"+tip+"</p>"
                         +'<button class="btn btn-md btn-block btn-primary" onclick = "showBill(this)" data-accountid="'+accountId+'">查看帐单</button>';
                	 });
            	 }
             },error: function(){
				alert("数据请求发送失败");
             }
         });
    });
    	
</script>
</body>

</html>