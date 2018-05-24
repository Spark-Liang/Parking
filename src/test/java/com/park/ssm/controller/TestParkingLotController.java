package com.park.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.park.AutoLoginTest;
import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.type.ParkingLotState;

import junit.framework.Assert;

//使用MockMvcRequestBuilders的静态方法生成mockrequest
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class TestParkingLotController extends AutoLoginTest {
	
	
	MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
	Map<String, String> paramsMap;
	
	
	
	
	public TestParkingLotController() {	
		
	}
	
	@Before
	public void setUpLogin() {
		innerUser.setName("12345678910");
		innerUser.setPassword("123456");
		innerUser.setTypeflag(0);
	}
	
	
	@Test
	public void testAdd() throws Exception {
		//测试参数
		MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
		Map<String, String> paramsMap;
		paramsMap=new HashMap<>();
		
		paramsMap.put("totalPositionNum", "100");
		paramsMap.put("currentPrice", "321");
		paramsMap.put("name", "ABCD");
		paramsMap.put("location", "ABCDE");
		paramsMap.put("cost", "100");
		
		
		for(String key:paramsMap.keySet()) {
			params.add(key, paramsMap.get(key));
		}
		ResultActions mockRequest=mockMvc.perform(
					post("/parkinglot/add")
					.session(session)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.params(params)
					);
		
		MockMvcResultHandlers.print(System.out).handle(mockRequest.andReturn());
		mockRequest.andExpect(status().isOk());
		
	}
	

	
	@Test
	public void testUpdate() throws Exception {
		//测试参数
		MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
		Map<String, String> paramsMap;
		paramsMap=new HashMap<>();
		
		paramsMap.put("id", "1");
		paramsMap.put("totalPositionNum", "100");
		paramsMap.put("currentPrice", "321");
		paramsMap.put("name", "ABCD");
		paramsMap.put("location", "ABCDE");
		paramsMap.put("cost", "100");
		
		
		for(String key:paramsMap.keySet()) {
			params.add(key, paramsMap.get(key));
		}
		ResultActions mockRequest=mockMvc.perform(
					post("/parkinglot/update")
					.session(session)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.params(params)
					);
		MockMvcResultHandlers.print(System.out).handle(mockRequest.andReturn());
		mockRequest.andExpect(status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception {
		
		//测试参数
		Integer id=1;
		
		MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
		Map<String, String> paramsMap;
		paramsMap=new HashMap<>();
		
		paramsMap.put("id", id.toString());
		
		
		for(String key:paramsMap.keySet()) {
			params.add(key, paramsMap.get(key));
		}
		ResultActions mockRequest=mockMvc.perform(
					post("/parkinglot/delete")
					.session(session)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.params(params)
					);
		MockMvcResultHandlers.print(System.out).handle(mockRequest.andReturn());
		mockRequest.andExpect(status().isOk());
		Map<String,Object> resultmap=new HashMap<>();
		resultmap.put("res", true);
		
		
		ParkingLotDao dao=applicationContext.getBean(ParkingLotDao.class);
		ParkingLot parkingLotResult=dao.loadParkingLotById(id);
		Assert.assertEquals(parkingLotResult.getState(),ParkingLotState.INACTIVE);
		
	}
}
