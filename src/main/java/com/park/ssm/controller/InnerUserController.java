package com.park.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.park.ssm.service.InnerUserService;

@Controller
public class InnerUserController {
	@Autowired
	private InnerUserService innerService;
	
	@RequestMapping("/pages/admin.html") 
	public String login(String nickname,String password) {
		
		return "admin.html";	
	}
}
