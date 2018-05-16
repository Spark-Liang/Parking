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
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="<%=path %>/js/jquery-3.3.1.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

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
.addparking img{
    display: block;
    margin: 20px auto;
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
.add-block{
    position: fixed;
    width: 400px;
    height: 450px;
    z-index: 1001;
    margin: 200px 0 0 35%;
    border-radius: 5px;
    border: 1px solid grey;
    padding: 0 50px;
    background-color: white;
    opacity: 0.9;
}
.add-block2{
    position: fixed;
    width: 100%;
    height: 100%;
    background-color: black;
    opacity: 0.5;
    z-index: 1000;

}
.add-block-one h3{
    text-align: center;
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
    border: 1px solid black;
    z-index: 1001;
    border-radius: 5px;
    padding: 0 40px;
}
.selectall-1 h3{
    margin-bottom: 30px;
}
.selectall-1 button{
    position: absolute;
    bottom: 50px;
    right: 50px;
}
</style>
<body>
    <div class="add-block" style="display: none;">
        <div class="add-block-one">
            <h3>添加一个停车场</h3>
            <br/>
            <input type="text" class="form-control" name="">
            <form>
                <div class="form-gorup">
                    <label>*新停车场名称</label>
                    <input type="text" class="form-control" name="" placeholder="New parking name">
                </div>
                <div class="form-gorup">
                    <label>*地址</label>
                    <input type="text" class="form-control" name="" placeholder="Parking address">
                </div>
                <div class="form-gorup">
                    <label>*停车位</label>
                    <input type="number" class="form-control" name="" placeholder="Parking num">
                </div>
                <br/>
                <div class="form-gorup">
                    <label>*状态:</label>
                    <input type="radio" name="radio" value="1">可用
                    <input type="radio" name="radio" value="2">不可用
                    <input type="radio" name="radio" value="3">单选3
                </div>
                <button class="btn btn-md btn-primary pull-right">提交</button>
            </form>
        </div>
        <div class="add-block-one">
            <h3>添加一个经理</h3>
            <br/>
            <form>
                <div class="form-gorup">
                    <label>*新经理名字</label>
                    <input type="text" class="form-control" name="" placeholder="New parking name">
                </div>
                <div class="form-gorup">
                    <label>*联系方式</label>
                    <input type="number" class="form-control" name="" placeholder="Parking num">
                </div>
                <div class="form-gorup">
                    <label>*工资</label>
                    <input type="number" class="form-control" name="" placeholder="Parking num">
                </div>
                <br/>
                <div class="form-gorup">
                    <label>*性别</label>
                    <input type="radio" name="sex" value="0">男
                    <input type="radio" name="sex" value="1">女
                </div>
                <button class="btn btn-md btn-primary pull-right">提交</button>
            </form>
        </div>
        <div class="add-block-one">
            <h3>添加一个操作员</h3>
            <br/>
            <form>
                <div class="form-gorup">
                    <label>*新操作员名字</label>
                    <input type="text" class="form-control" name="" placeholder="New parking name">
                </div>
                <div class="form-gorup">
                    <label>*性别</label>
                    <input type="text" class="form-control" name="" placeholder="Parking address">
                </div>
                <div class="form-gorup">
                    <label>*联系方式</label>
                    <input type="number" class="form-control" name="" placeholder="Parking num">
                </div>
                <div class="form-gorup">
                    <label>*工资</label>
                    <input type="number" class="form-control" name="" placeholder="Parking num">
                </div>
                <br/>
                <div class="form-gorup">
                    <label>*性别</label>
                    <input type="radio" name="sex" value="0">男
                    <input type="radio" name="sex" value="1">女
                </div>
                <button class="btn btn-md btn-primary pull-right">提交</button>
            </form>
        </div>
    </div>
    <div class="selectall-1">
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
    <div class="add-block2" style="display: none;">
    </div>
    <div class="dh">
        <label>Parking management system</label>
        <a class="btn btn-primary btn-md"  href="login.html">退出</a>
    </div>
    <div class="container container-style" >
        <h1>Hello,XXX</h1>
        <p class="text-info">你的身份是XXX,可以操作以下数据</p>
        <ol class="breadcrumb">
            <li><a onclick="skip(0)">停车场</a></li>
            <li><a onclick="skip(1)">经理</a></li>
            <li><a onclick="skip(2)">操作员</a></li>
        </ol>
        <div class="moudle-one moudle1 " >
            <h4>停车场管理</h4>
            <p class="selectall"><img src="<%=path %>/img/selectall.svg">批量管理</p>
            <div class="admin-block addparking">
                <img src="admin-add.svg">
            </div>
            <div class="admin-block">
                <h3>停车场1</h3>
                <p>地址：</p>
                <p>停车位：</p>
                <p>成本：</p>
                <p>状态：</p>
                <button class="btn btn-md btn-block btn-primary">编辑</button>
            </div>

        </div>
        <div class="moudle-one moudle2" style="display: none;">
            <h4>经理管理</h4>
            <p class="selectall"><img src="<%=path %>/img/selectall.svg">批量管理</p>
            <div class="admin-block addparking ">
                <img src="<%=path %>/img/admin-add.svg">
            </div>
            <div class="admin-block manger">
                <img src="<%=path %>/img/admin-tx.svg">
                <p>姓名：</p>
                <p>职位：</p>
                <p>电话：</p>
                <p>性别：</p>
                <button class="btn btn-md btn-block btn-primary">编辑</button>
            </div>
            <div class="admin-block">
                
            </div>
        </div>
        <div class="moudle-one moudle3" style="display: none;">
            <h4>操作员管理</h4>
            <p class="selectall"><img src="<%=path %>/img/selectall.svg">批量管理</p>
            <div class="admin-block addparking">
                <img src="<%=path %>/img/admin-add.svg">
            </div>
            <div class="admin-block opperation">
                <img src="<%=path %>/img/admin-tx.svg">
                <p>姓名：</p>
                <p>职位：</p>
                <p>电话：</p>
                <p>性别：</p>
                <button class="btn btn-md btn-block btn-primary">编辑</button>
            </div>
            <div class="admin-block">
                
            </div>
        </div>
        
    </div>

    
<script type="text/javascript">
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
</script>
</body>
</html>