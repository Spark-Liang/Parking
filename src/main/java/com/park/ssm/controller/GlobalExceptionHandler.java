package com.park.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;  
import org.apache.logging.log4j.LogManager;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GlobalExceptionHandler {
	private Logger logger=LogManager.getLogger(this.getClass());
	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@RequestMapping(value="error",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
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
	
	
	
	@RequestMapping(value="error")
	public String handleException(HttpServletRequest request,ModelMap modelMap) {
		Exception e=(Exception) request.getAttribute("error");
		logger.info("method:handleException");
		logger.info(e);
		modelMap.addAttribute("error", "message:"+e.getMessage());
		return "errorInfoPage";
	}
	
}
