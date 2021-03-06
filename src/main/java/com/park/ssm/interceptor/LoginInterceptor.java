package com.park.ssm.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.alibaba.fastjson.JSON;
import com.park.ssm.annotation.Permission;
import com.park.ssm.entity.InnerUser;
import com.park.ssm.exception.LoginException;
import com.park.ssm.util.CommonsDebugFlag;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static String loginUrl="/inneruser/page";
	private static String errorUrl="";
	
	private Logger logger=LogManager.getLogger(this.getClass());
	
	private static boolean debugFlag=CommonsDebugFlag.isDebug();
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) 
		throws IOException, ServletException{
		if(debugFlag) {
			logger.info("intercept request url:"+request.getServletPath());
		}else {
			logger.debug("intercept request url:"+request.getServletPath());
		}
		//忽略指向登录页面的请求
		if(request.getServletPath().equals(loginUrl)) {
			if(debugFlag) {
				logger.info("忽略指向登录页面的请求\n");
			}
			return true;
		}
		//非HandlerMethod的请求不需要进行权限控制
		if(!(handler instanceof HandlerMethod)) {
			if(debugFlag) {
				logger.info("非HandlerMethod的请求不需要进行权限控制\n");
			}
			return true;
		}
		//检查是否需要进行权限控制
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		Method method=handlerMethod.getMethod();
		Permission permission=method.getAnnotation(Permission.class)!=null?method.getAnnotation(Permission.class):method.getDeclaringClass().getAnnotation(Permission.class);
		if(permission==null || permission.haveControl()==false) {
			//不需要进行权限控制
			if(debugFlag) {
				logger.info("request servlet url:"+request.getServletPath());
				logger.info("不需要进行权限控制\n");
			}
			return true;
		}
		//检查是否登录登录
		HttpSession session=request.getSession();
		InnerUser innerUser=(InnerUser) session.getAttribute("innerUser");
		if(innerUser==null) {  
			//未登录，重定向到登录页面  
			//request.getRequestDispatcher(loginUrl).forward(request, response);
			//response.sendRedirect(request.getContextPath()+loginUrl);
			LoginException exception=new LoginException("未登录请重新登录",loginUrl);
			if(debugFlag) {
				logger.info("未登录，重定向到登录页面 \n");
				logger.info(exception);
			}
			throw exception;
			//return false;  
		}else {
			//已登录，检查权限是否满足要求
			if(checkPermission(permission, innerUser)) {
				if(debugFlag) {
					logger.info("已登录，检查权限是否满足要求 \n");
				}
				return true;
			}else {
				//权限无法满足要求
				//如果访问返回json的方法，则返回 {“error”：“错误信息”}
				if(method.getAnnotation(ResponseBody.class)!=null) {
					Map<String, String> result=new HashMap<>();
					result.put("error", "权限不足无法操作");
					PrintWriter writer=response.getWriter();
					writer.write(JSON.toJSONString(result));
					writer.flush();
					return false;
				}
				//如果访问返回jsp的方法，则跳转到错误页面 
				request.setAttribute("message", "权限不足无法操作");
				request.setAttribute("originURL", request.getRequestURL());
				RequestDispatcher dispatcher=request.getRequestDispatcher(errorUrl); 
				dispatcher.forward(request, response);
				if(debugFlag) {
					logger.info("如果访问返回jsp的方法，则跳转到错误页面 \n");
					logger.info(dispatcher);
				}
				return false;
			}
		}
	}
	/**
	 * 检查是否符合权限要求
	 * @param permission
	 * @param innerUser
	 * @return
	 */
	private boolean checkPermission(Permission permission,InnerUser innerUser) {
/*		//判断注解的方法是否需要权限控制
		if(permission.haveControl()==false) {
			return true;
		}*/
		int typeflag=innerUser.getTypeflag();
		for(Permission.Type permissionRole:permission.value()) {
			if(permissionRole.getInd()==typeflag){
				return true;
			}
		}
		return false;
	}
	
	
}
