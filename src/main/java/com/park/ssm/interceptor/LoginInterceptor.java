package com.park.ssm.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.park.ssm.annotation.Permission;
import com.park.ssm.entity.InnerUser;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static String loginUrl="/login/page";
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) 
		throws IOException, ServletException{
		System.out.println("intercept");
		//忽略指向登录页面的请求
		if(request.getServletPath().equals(loginUrl)) {
			return true;
		}
		
		
		//检查是否需要进行权限控制
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		Method method=handlerMethod.getMethod();
		Permission permission=method.getAnnotation(Permission.class)!=null?method.getAnnotation(Permission.class):method.getClass().getAnnotation(Permission.class);
		if(permission==null) {
			//不需要进行权限控制
			return true;
		}
		//检查是否登录登录
		HttpSession session=request.getSession();
		InnerUser innerUser=(InnerUser) session.getAttribute("innerUser");
		if(innerUser==null) {  
			//未登录，重定向到登录页面  
			request.getRequestDispatcher(request.getContextPath()+loginUrl).forward(request, response); 
			return false;  
		}else {
			//已登录，检查权限是否满足要求
			if(checkPermission(permission, innerUser)) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath() + "error");  
				return false;  
			}
		}
	}
	
	public boolean checkPermission(Permission permission,InnerUser innerUser) {
		int typeflag=innerUser.getTypeflag();
		for(Permission.Type permissionRole:permission.value()) {
			if(permissionRole.getInd()==typeflag){
				return true;
			}
		}
		return false;
	}
}
