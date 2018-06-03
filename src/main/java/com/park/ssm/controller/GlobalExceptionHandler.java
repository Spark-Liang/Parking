package com.park.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;  
import org.apache.logging.log4j.LogManager;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.ssm.exception.LoginException;

@Controller
@RequestMapping(value="error")
public class GlobalExceptionHandler {
	private Logger logger=LogManager.getLogger(this.getClass());
	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@RequestMapping(value="",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody Map handleExceptionToJSON(HttpServletRequest request) {
		Exception e=(Exception) request.getAttribute("error");
		logger.info("method:handleExceptionToJSON");
		logger.info(e);
		return new HashMap() {
			{
				put("error", e.getMessage());
			}
		};
	}
	
	
	
	@RequestMapping(value="")
	public String handleException(HttpServletRequest request,ModelMap modelMap) {
		Exception e=(Exception) request.getAttribute("error");
		logger.info("method:handleException");
		logger.info(e);
		modelMap.addAttribute("error", "message:"+e.getMessage());
		return "errorInfoPage";
	}
	
	/**
	 * 处理未登录，需要跳转登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/login",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map handleLoginExceptionInReturnJSON(HttpServletRequest request,HttpServletResponse response) {
		response.setStatus(403);
		Map<String, Object> result=new HashMap<>();
		LoginException exception=(LoginException) request.getAttribute("error");
		result.put("error", exception.getMessage());
		result.put("url",  request.getContextPath()+exception.getLoginUrl());
		return result;
	}
	
	/**
	 * 处理未登录，需要跳转登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",produces=MediaType.TEXT_HTML_VALUE)
	public String handleLoginExceptionInReturnHTML(HttpServletRequest request,HttpServletResponse response) {
		LoginException exception=(LoginException) request.getAttribute("error");
		return "forward:"+exception.getLoginUrl();
	}
}
