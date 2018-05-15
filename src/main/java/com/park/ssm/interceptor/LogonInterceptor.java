package com.park.ssm.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.park.ssm.entity.LogonBean;


public class LogonInterceptor extends HandlerInterceptorAdapter {
	private static String loginUrl="/WEB-INF/pages/login.html";
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) 
		throws IOException{
		
		//请求到登录页面 放行 
		if(request.getServletPath().startsWith(loginUrl)) {  
		    return true;  
		}
		
		HttpSession session=request.getSession();
		LogonBean typeflag=(LogonBean) session.getAttribute("logon");
		if(typeflag==null) {
			//非法请求 即这些请求需要登录后才能访问  
			//重定向到登录页面  
			response.sendRedirect(request.getContextPath() + loginUrl);  
			return false;  
		}else {
			return true;
		}
		
	}
}
