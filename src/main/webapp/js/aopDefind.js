/**定义全局的AOP配置
 * 
 */

/**用于生成添加前后处理逻辑的闭包函数
 * 
 * @param pointCutFunc
 * @param adviceFunc
 * @returns
 */
function weav(pointCutFunc,adviceFunc){
	return function(){
		var result=null;
		if(adviceFunc.before != null && typeof adviceFunc.before === "function"){
			result=adviceFunc.before.apply(this,arguments);
		}else{
			result=true;
		}
		if(result != false){
			if(pointCutFunc.joinPoint != null && typeof pointCutFunc.joinPoint === "function"){
				pointCutFunc.joinPoint.apply(this,arguments);
			}
		}
		if(adviceFunc.after != null && typeof adviceFunc.after === "function"){
			adviceFunc.after.apply(this,arguments);
		}
	}
}

/**
 * 用于生成Aop的切入点对象
 * @param joinPoint 需要进行控制的函数
 * @returns
 */
function PointCutFunc(joinPoint){
	this.joinPoint=joinPoint;
}

/**
 * 生成加入前后处理逻辑的对象
 * @param option<br> 
 * 其中的处理逻辑函数返回true表示继续执行，返回false表示直接跳出不执行下一步
 * 	{ <br>
 * 		before:表示joinPoint前的处理逻辑<br>
 * 		after:表示joinPoint后的处理逻辑<br>
 * 	}
 * @returns
 */
function AdviceFunc(option){
	var attrNames=["before","after"];
	var AdviceFunc_this=this;
	attrNames.forEach(function(value,index){
		var key=value;
		if(typeof option[key] === "function"){
			AdviceFunc_this[key]=option[key];
		}
	});
}


//ajax处理函数的处理逻辑
//处理返回成功的函数
var successAdvice=new AdviceFunc({
	before:function(response){
		if(response.error != null && response.error != ""){
			alert(response.error)
			return false;
		}
		return true;
	}
});
//处理返回失败的函数
var errorAdvice=new AdviceFunc({
	before:function(XMLHttpRequest, textStatus, errorThrown){
		if(XMLHttpRequest.status == '403'){
			var responseJSON=XMLHttpRequest.responseJSON
			if(responseJSON.error != null ){
				alert(responseJSON.error);
			}
			window.location.pathname=responseJSON.url;
		}
		return true;
	}
});

/**
 * 向jquery中的ajax函数织入处理逻辑
 * @param option
 * @returns
 */
function ajaxAop(){
	var _ajax=jQuery.ajax;
	var ajaxJoinPoint=new PointCutFunc(_ajax);
	var ajaxAdvice=new AdviceFunc({
		before:function(url,option){
			//获取ajax的option
			var optionInput=null;
			if ( typeof url === "object" ) {
				optionInput=url;
			}else{
				optionInput=option;
			}
			//设置success的advice函数
			if(typeof optionInput.success ==="function"){
				var successJoinPoint=new PointCutFunc(optionInput.success);
				optionInput.success=weav(successJoinPoint,successAdvice);
			}
			//设置error的advice函数
			if(typeof optionInput.error ==="function"){
				var errorJoinPoint=new PointCutFunc(optionInput.error);
				optionInput.error=weav(errorJoinPoint,errorAdvice);
			}
		}
	});
	jQuery.ajax=weav(ajaxJoinPoint,ajaxAdvice);
} 
ajaxAop();


