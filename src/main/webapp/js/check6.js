//账号检测
function checkAdmin(className,tip){
        var name = document.getElementsByClassName(className)[0];
        var uName = name.value.trim();
        var nameLength = uName.length;
        var regex = new RegExp("^[0-9]+$");
        if (nameLength < 1) {
            tip.innerHTML = "请输入账号";
            name.focus();
            return false;
        }else if(nameLength !== 11){
            tip.innerHTML = '账号长度为11位';
            name.focus();
            return false;
        }else if(uName.indexOf(' ') !== -1){
            tip.innerHTML = '账号中间不能有空格';
            name.focus();
            return false;
        }else if(!regex.test(uName)){
            tip.innerHTML = "账号只能包含数字";
            name.focus();
            return false;
        }
        return true;
    }


//持卡普通用户账号检测
function checkName(className,tip){
        var name = document.getElementsByClassName(className)[0];
        var uName = name.value.trim();
        var nameLength = uName.length;
        var regex = new RegExp("^[0-9a-zA-Z]+$");
        if (nameLength < 1) {
            tip.innerHTML = "请输入账号";
            name.focus();
            return false;
        }else if(nameLength > 16){
            tip.innerHTML = '账号长度不能超出16位';
            name.focus();
            return false;
        }else if(uName.indexOf(' ') !== -1){
            tip.innerHTML = '账号中间不能有空格';
            name.focus();
            return false;
        }else if(!regex.test(uName)){
            tip.innerHTML = "账号只能包含英文和数字";
            name.focus();
            return false;
        }
        return true;
    }
//密码检测
function checkPwd(className,tip){
        
    var pass = document.getElementsByClassName(className)[0];
    var passValue = pass.value.trim();
    var passLength = passValue.length;
    if(passLength < 1){
        tip.innerHTML = "请输入密码";
        pass.focus();
        return false;
    
    }else if(passValue.indexOf(' ') !== -1){
        tip.innerHTML = '密码中间不能有空格';
        pass.focus();
        return false;
    }
    return true;
    }

//清空出错提示
function cleanTip(tip){
    tip.innerHTML = '';
}

