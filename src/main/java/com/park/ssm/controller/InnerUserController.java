package com.park.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.park.ssm.service.InnerUserService;

@Controller
public class InnerUserController {
	@Autowired
	private InnerUserService innerService;
	
}
