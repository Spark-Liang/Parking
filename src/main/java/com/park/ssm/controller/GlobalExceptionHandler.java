package com.park.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GlobalExceptionHandler {
	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	@RequestMapping(value="/errorhandle",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody Map handleExceptionToJSON(Exception e) {
		return new HashMap() {
			{
				put("error", e.toString());
			}
		};
	}
	
	
	@RequestMapping(value="/errorhandle",produces= {MediaType.TEXT_HTML_VALUE})
	public String handleExceptionToHTML(Exception e) {
		return "errorInfoPage";
	}
	
	@RequestMapping(value="/errorhandle")
	public String handleException(Exception e) {
		return "errorInfoPage";
	}
}
