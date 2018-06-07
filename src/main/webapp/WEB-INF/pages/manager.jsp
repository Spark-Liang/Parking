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
    <title>经理操作页面</title>
    <link rel="stylesheet" href="css/manger.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="js/laydate.js"></script> 
	<script src="js/AllUseTools.js"></script>
</head>

<style type="text/css">
    .btn-use{
     
        top: 170px;
        height: 34px;
        
    }
    .showUsage{
        position: fixed;
        width: 100%;
        height: 100%;
        background-color: white;
        z-index: 1000;
        display: none;
    }
    .showUsage-1{
        width: 800px;
        height: 70%;
        margin: 4% auto;
    }
</style>
<body>

    <div class="add-block2" style="display: none;">
    </div>
    <div class = "showUsage">
        <div class = "showUsage-1">
            <div id="usageClose">
                <img src="img/manger-close2.svg">
                <strong>关闭</strong>
            </div>
            <div>
                <br/>
                <h4>按月查询</h4>
                <div class="input-group manger-month">
                    <input type="text" class="demo-input form-control" placeholder="年月选择器" id="test4">
                    <span class="input-group-btn">
                        <button class="btn btn-primary">查询</button>
                    </span>
                </div>

            </div>
                <br/>
                <h4>停车场1</h4>
                <p>地址：********************</p>
                <table class="table manger-table table-condensed" align = "center">
                    <thead>
                        <tr>
                            <th>车牌号</th>
                            <th>使用次数</th>                  
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>861314</td>
                            <td>52</td>
                            
                    </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td>861314</td>
                            <td>52</td>
                            
                    </tr>
                    </tbody>
                    <tbody>
                        <tr>
                            <td>861314</td>
                            <td>52</td>
                            
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--销售和收入查询-->
    <div class="showmoney">
        <div class="showmoney-1">
            <div id="showmoney-close">
                <img src="img/manger-close2.svg">
                <strong>关闭</strong>
            </div>
            <!--<h2>停车场1</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>停车场名字</th>
                        <th>地址</th>
                        <th>车位数量</th>
                        <th>售出</th>
                        <th>收入</th>
                        <th>总盈利</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>停车场名字</td>
                        <td>地址</td>
                        <td>车位数量</td>
                        <td>售出</td>
                        <td>总盈利</td>
                    </tr>
                </tbody>
            </table>-->
            <br/>
            <h4>按月查询</h4>
            <div class="input-group manger-month">
                <input type="text" class="demo-input form-control" placeholder="年月选择器" id="test3">
                <span class="input-group-btn">
                    <button class="btn btn-primary lookBtn">查询</button>
                </span>
            </div>
            <br/>
            <h4>停车场1</h4>
            <p>地址：********************</p>
            <table class="table manger-table table-condensed">
                <thead>
                    <tr>
                        <th>车位数量</th>
                        <th>已售出车位</th>
                        <th>已出帐单金额</th>
                        <th>已收款金额</th>
                        <th class = "nextQuarter">下季度预计收入</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>****</td>
                        <td>***</td>
                        <td>****</td>
                        <td>***</td>
                        <td class = "nextQuarter">***</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="dh">
        <label>Parking management system</label>
        <a class="btn btn-primary btn-md" href="<%=basePath%>inneruser/logout">退出</a>
    </div>
    <div class="container container-style" >
        <h1>Hello,<span id='manger-name'></span></h1>
        <p class="text-info">您的身份是经理,可以操作以下数据</p>
        <div class="moudle-one moudle1 " >
            <h4>停车场管理</h4>
            <!-- <div class="admin-block">
                <h3>停车场1</h3>
                <p>地址：</p>
                <p>总停车位：</p>
                <div class="parking-money">
                    <p>停车费：￥<span></span>/月<img onclick="editmoney(this)" class="pull-right" src="img/manger-editor.svg"></p>
                </div>
                
                <button class="btn btn-md btn-block btn-primary btn-use" onclick="usagecheck(this)">查看使用情况</button>
                <button class="btn btn-md btn-block btn-primary" onclick="moneycheck(this)">查看销售和收入</button>
            </div> -->
        </div>
    </div>
       
<script type="text/javascript">
    var dateChoose;
    //关闭察看使用情况的div
    $('#usageClose').click(function(){
        $('.showUsage').fadeOut();
    });
    //使用情况页面点击显示函数
    function usagecheck(a){
        $('.showUsage').fadeIn();
    }

    //关闭经理查看的div
    $('#showmoney-close').click(function(){
        $('.showmoney').fadeOut();
    });
    function moneycheck(a){
        $('.showmoney').fadeIn();
    }
    //年月选择器
    laydate.render({
      elem: '#test4'
      ,type: 'month'
    });
    laydate.render({
      elem: '#test3'
      ,type: 'month'
      ,done: function(value, date, endDate){//控件选择完毕后的回调---点击日期、清空、现在、确定均会触发。
       
        dateChoose = date;     //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
      }
    });
    
    //修改价格
    function editmoney(a){
    	//判断是不是出单日
    	var mydate = new Date();
    	var mymonth = mydate.getMonth();
    	var myday = mydate.getDate();
    	//console.log(typeof mymonth);
    	if(mymonth === 0 || mymonth === 3 || mymonth === 6 || mymonth === 9){
    		if(myday === 1){
    			alert("今天为出单日，不能修改价格");
    			return;
    		}
    	}
    	
        $('.edit-money').remove();
        var id = $(a).data('value');
        var content = '2到5位正整数';
        $(a).parent().parent().append(function(){
        	//id是停车场的id content是输入框中的提示信息
            return NumEdit(id,content);
        })
    }
    function moneyok(a){
    	var id = $(a).data('value');
        var aa = $(a).parent().parent().find('input').val();
       /*  $(a).parent().parent().parent().find('span:eq(0)').text(aa); */
        var object = /^[1-9]{1}\d{1,4}$/;
            if(object.test(aa)){
            	$.ajax({
                    url:'inneruser/changeParkingLotPrice',
                    type:'POST',
                    dataType:'json',
                    data:{
                    	'id':id,
                    	'currentPrice':aa
                    },success:function(json){
                    	var flag = "出账日不能修改价格";
                    	console.log(json)
                    	if(json.msg===flag){
                    		alert(flag);
                    		return;
                    	}
                        if(json.msg==1){
                        	alert('修改成功')
                             $(a).parent().parent().parent().find('span:eq(0)').text(aa);
                             $(a).parent().parent().fadeOut();
                        }else if(json.error){
                        	alert('程序内部错误，修改失败，请重试！');
                        }
                    },error:function(){

                    }
               })
            }else{
            	alert("价格只能为2到5位的正整数");
            }  
    }
    // 页面加载用户信息
    $(window).ready(function(){
        var id = window.location.href.split("=")[1];
        var href = decodeURI(window.location.href);
		var adminname = href.split("=")[2];
		$('#manger-name').text(adminname);
        $.ajax({
         url:'parkinglot/list',
         type:'GET',
         dataType:'json',
         data:{
        	 
         },success:function(json){
        	 console.log(json.res)
             var l = json.res.length;
             for(var i = 0;i<l;i++){
             $('.moudle1').append(function(){
                 return "<div class='admin-block'>"
                 +"<h3>"+json.res[i].name+"</h3>"
                 +"<p>地址："+json.res[i].location+"</p>"
                 +"<p>总停车位："+json.res[i].totalPositionNum+"</p>"
                 +"<div class='parking-money'>"
                 +"<p>停车费：￥<span>"+json.res[i].currentPrice+"</span>元/月<img onclick='editmoney(this)' data-value='"+json.res[i].id+"' class='pull-right' src='img/manger-editor.svg'></p>"
                 +"</div>"
                /*  +"<button class='btn btn-md btn-block btn-primary btn-use' onclick='usagecheck(this)'>查看使用情况</button>" */
                 +"<button class='btn btn-md btn-block btn-primary' onclick='moneycheck(this)'>查看销售和收入</button>"
                 +"</div>"
             })
             }
         },error:function(){

         }
     })
    })
    
    //ajax获取停车场价格
    /*$(window).ready(function(){
        $.ajax({
            url:"",
            type:"GET",
            dataType:"json",
            data:{

            },
            success:function(json){
                $('.parking-money span').text(json);
            },
            error:function(){

            }
        });
    })*/
    //选择日期后点击查询，ajax传日期以及获取相应数据
    $('.nextQuarter').fadeOut();
    var lookBtn = document.getElementsByClassName("lookBtn")[0];
    lookBtn.addEventListener("click",function(){
        var newDate = new Date();
        if(dateChoose){
            if(dateChoose.year == newDate.getFullYear() && dateChoose.month == (newDate.getMonth() + 1)){
                $('.nextQuarter').fadeIn();
            }
            else{
                $('.nextQuarter').fadeOut();
            }
        }
    });
</script>
</body>

</html></html>