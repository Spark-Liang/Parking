package com.park.ssm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalController {
	private static String errorHandleUrl="/error";
	
	@ExceptionHandler(value= {Exception.class})
	public String exception(Exception e,HttpServletRequest request){
		request.setAttribute("error", e);
		return "forward:"+errorHandleUrl;
	}

}
