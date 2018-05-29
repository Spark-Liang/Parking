package com.park.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalController {
	private static String errorHandleUrl="/error";
	
	@ExceptionHandler(value= {Exception.class})
	public String exception(Exception e,HttpServletRequest request){
		request.setAttribute("error", e);
		return "forward:"+errorHandleUrl;
	}

}
