package com.park.ssm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalController {
	private static String errorHandleUrl="/errorhandle";
	
	@ExceptionHandler(value= {Exception.class})
	public String exception(Exception e,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher(errorHandleUrl).forward(request, response);
		return "forward:"+errorHandleUrl;
	}
	
	
	
}
