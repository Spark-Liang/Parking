<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="js/laydate/laydate.js"></script> 

</head>

<style type="text/css">
.dh{
    width: 100%;
    height: 50px;
    background-color: #222;
}
.dh label{
    height: inherit;
    line-height: 50px;
    color: white;
    font-size: 15px;
    margin-left: 15px;
}
.container-style{
    padding: 0 30px;
}
.container-style h1{
    font-size: 50px;
    height: 70px;
    line-height: 70px;
    margin: 70px 0 0 0;
    border-bottom: 2px solid grey;
}
.container-style ol{
    margin-bottom: 10px;
}
.moudle-one{
    width: 100%;
    min-height: 500px;
    padding: 0 20px;
}
.moudle-one h4{
    text-align: center;
    font-weight: 600;
    font-size: 23px;
    margin-bottom: 20px;
}
.admin-block{
    width: 220px;
    height: 250px;
    border-top: 2px solid grey;
    margin: 0 20px 20px 0;
    border-radius: 3px;
    float: left;
    padding: 0 10px;
    position: relative;
}
.admin-block p{
    margin: 0;

}
.admin-block button{
    position: absolute;
    bottom: 0px;
    width: 93%
}
.manger img{
    display: block;
    margin: 10px auto;
    width: 100px;
    height: 100px;
}
.opperation img{
    display: block;
    margin: 10px auto;
    width: 100px;
    height: 100px;
}

.selectall{
    margin-top: -30px;
}
.selectall-1{
    display: none;
    width: 550px;
    height: 450px;
    position: fixed;
    margin: 200px 0 0 30%;
    background-color: white;
    box-shadow: 1px 1px 2px 2px grey;
    z-index: 1001;
    border-radius: 5px;
    padding: 30px 40px 0 40px;
}
.selectall-1 h3{
    margin-bottom: 30px;
    margin-top: 0px;
}
.selectall-1 button{
    position: absolute;
    bottom: 50px;
    right: 50px;
}
.add-block2{
    position: fixed;
    width: 100%;
    height: 100%;
    background-color: black;
    opacity: 0.5;
    z-index: 1000;
}
.showmoney{
    position: fixed;
    width: 100%;
    height:100%;
    background-color: white;
    z-index:1000;
    display: none;
}
.showmoney-1{
    width: 60%;
    height: 70%;
    margin: 4% auto;
}
.manger-month{
    width: 40%;
}
.manger-table{
    display:block; max-height:450px;overflow-y: scroll;
    width: 100%;
}
.manger-table th{
    min-width: 140px;
}
</style>
<body>
    <div class="add-block2" style="display: none;">
    </div>
    <div class="selectall-1">
        <span class="btn btn-danger btn-xs pull-right">关闭</span>
        <h3>批量管理</h3>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th></th>
                    <th>fdasfdas</th>
                    <th>fdasfdas</th>
                    <th>fdasfdas</th>
                    <th>fdasfdas</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input type="checkbox" name=""></td>
                    <td>fdasfdas</td>
                    <td>fdasfdas</td>
                    <td>fdasfdas</td>
                    <td>fdasfdas</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name=""></td>
                    <td>fdasfdas</td>
                    <td>fdasfdas</td>
                    <td>fdasfdas</td>
                    <td>fdasfdas</td>
                </tr>
            </tbody>
        </table>
        <button class="btn btn-md btn-primary pull-right">删除</button>
    </div>
    <div class="showmoney">
        <div class="showmoney-1">
            <div id="showmoney-close">
                <img src="img/manger-close2.svg">
                <strong>关闭</strong>
            </div>
            <h2>停车场1</h2>
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
            </table>
            <br/>
            <h4>按月查询</h4>
            <div class="input-group manger-month">
                <input type="text" class="demo-input form-control" placeholder="年月选择器" id="test3">
                <span class="input-group-btn">
                    <button class="btn btn-primary">查询</button>
                </span>
            </div>
            <br/>
            <table class="table manger-table table-condensed">
                <thead>
                    <tr>
                        <th>停车场名字</th>
                        <th>地址</th>
                        <th>车位数量</th>
                        <th>售出</th>
                        <th>收入</th>
                        
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
            </table>
        </div>
    </div>
    <div class="dh">
        <label>Parking management system</label>
        <a class="btn btn-primary btn-md" href="login.html">退出</a>
    </div>
    <div class="container container-style" >
        <h1>Hello,XXX</h1>
        <p class="text-info">你的身份是XXX,可以操作以下数据</p>
        <div class="moudle-one moudle1 " >
            <h4>停车场管理</h4>
            <p class="selectall"><img src="img/selectall.svg">批量管理</p>
            <div class="admin-block">
                <h3>停车场1</h3>
                <p>地址：</p>
                <p>总停车位：</p>
                <div class="parking-money">
                    <p>停车费：<img onclick="editmoney(this)" class="pull-right" src="img/manger-editor.svg"></p>
                </div>
                <p>售出：</p>
                <p>剩余：</p>
                <button class="btn btn-md btn-block btn-primary" onclick="moneycheck(this)">点击查看</button>
            </div>
        </div>
    </div>
       
<script type="text/javascript">
    //关闭经理查看的div
    $('#showmoney-close').click(function(){
        $('.showmoney').fadeOut();
    });
    function moneycheck(a){
        $('.showmoney').fadeIn();
    }
    //年月选择器
    laydate.render({
      elem: '#test3'
      ,type: 'month'
    });
    function editmoney(a){
        $('.edit-money').remove();
        $(a).parent().parent().append(function(){
            return "<div class='input-group input-group-sm edit-money'><input type='text' class='form-control' placeholder='Parking money'>"
                        +"<span class='input-group-btn'>"
                            +"<button class='btn btn-default' type='button' onclick='moneyok(this)'><img src='img/manger-editor.svg'></button>"
                        +"</span>"
                        +"<span class='input-group-btn'>"
                            +"<button class='btn btn-default' type='button' onclick='moneyclose(this)'><img src='img/manger-close.svg'></button>"
                        +"</span>"
                    +"</div>";
        })
    }
    function moneyok(a){
        alert('ok')
    }
    function moneyclose(a){
        $('.edit-money').fadeToggle();
    }
    function skip(num){
        if(num==0){
            $('.moudle1').show();
            $('.moudle2,.moudle3').hide();
        }
        else if(num==1){
            $('.moudle2').show();
            $('.moudle1,.moudle3').hide();
        }
        else if(num==2){
            $('.moudle3').show();
            $('.moudle1,.moudle2').hide();
        }
    }
    $('.addparking:eq(0)').click(function(){
        $('.add-block,.add-block2').fadeToggle();
        $('.add-block-one:eq(0)').css("display","block");
        $('.add-block-one:eq(1),.add-block-one:eq(2)').css("display","none");
    })
    $('.addparking:eq(1)').click(function(){
        $('.add-block,.add-block2').fadeToggle();
        $('.add-block-one:eq(1)').css("display","block");
        $('.add-block-one:eq(0),.add-block-one:eq(2)').css("display","none");
    })
    $('.addparking:eq(2)').click(function(){
        $('.add-block,.add-block2').fadeToggle();
        $('.add-block-one:eq(2)').css("display","block");
        $('.add-block-one:eq(1),.add-block-one:eq(0)').css("display","none");
    })
    $('.add-block2').click(function(){
        $('.add-block,.add-block2,.selectall-1').fadeOut();
    })


    $('.selectall').click(function(){
        $('.selectall-1,.add-block2').fadeToggle();
    })
    $('.selectall-1 span').click(function(){
        $('.selectall-1,.add-block2').fadeToggle();
    })
</script>
</body>
</html>