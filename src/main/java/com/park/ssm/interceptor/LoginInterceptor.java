package com.park.ssm.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.park.ssm.annotation.Permission;
import com.park.ssm.entity.InnerUser;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static String loginUrl="/inneruser/page";
	private static String errorUrl="";
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) 
		throws IOException, ServletException{
		System.out.println("intercept");
		System.out.println("request:"+request.getServletPath());
		//忽略指向登录页面的请求
		if(request.getServletPath().startsWith("inneruser")) {
			return true;
		}
		//非HandlerMethod的请求不需要进行权限控制
		if(!(handler instanceof HandlerMethod)) {
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
				request.getRequestDispatcher(request.getContextPath()+errorUrl).forward(request, response); 
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
		int typeflag=innerUser.getTypeflag();
		for(Permission.Type permissionRole:permission.value()) {
			if(permissionRole.getInd()==typeflag){
				return true;
			}
		}
		return false;
	}
}
