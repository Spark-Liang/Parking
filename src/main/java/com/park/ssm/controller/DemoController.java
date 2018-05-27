package com.park.ssm.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.entity.ParkingLot;

@Controller
public class DemoController {
	@Resource
	private ParkingLotDao dao;
	
	@RequestMapping("Hello")
	public String Hello() {
		return "index";
	}
	
	@RequestMapping("test")
	public String test(ModelMap model) {
		ParkingLot parkingLot=dao.loadParkingLotById(1);
		model.put("test", parkingLot.toString());
		return "test";
	}
	
	@RequestMapping("testJSON")
	@ResponseBody
	public String testJson(@RequestParam(name="lotId") Integer[] idStr){
		//Integer id=Integer.valueOf(idStr[0]);
		ParkingLot parkingLot=dao.loadParkingLotById(idStr[0]);
		return JSON.toJSONString(parkingLot);
	}
	
	@RequestMapping("testParameter")
	@ResponseBody
	public String testParameter(HttpServletRequest request) {
		
		return JSON.toJSONString(request.getParameterMap());
	}
	
	@RequestMapping("index")
	public String testIndex(){
		return "index";
	}
	
	
	@RequestMapping("throw")
	public @ResponseBody Map testThrow() {
		throw new RuntimeException("test throw runtime exception");
	}
	
	@RequestMapping("toError")
	public String testToErrorHandler(HttpServletRequest request) {
		return "forward:/error";
	}
	
	@RequestMapping("testForward")
	public void testForward(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/error").forward(request, response);
	}
	
	@RequestMapping("testLogger")
	public String testLogger() {
		Logger logger=LogManager.getLogger("Console_OUT");
		logger.info("get logger and log this message in info");
		logger.debug("get logger and log this message in Debug");
		logger=LogManager.getLogger("RunTimelog");
		System.out.println(logger);
		logger.debug("log in file");
		Properties properties=System.getProperties();
		for(Object key:properties.keySet()) {
			System.out.println("{"+key+":"+properties.get(key)+"}");
		}
		return "welcome";
	}
}
