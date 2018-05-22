package com.park.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.park.AutoRollBackTest;
import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.type.ParkingLotState;

import junit.framework.Assert;

//使用MockMvcRequestBuilders的静态方法生成mockrequest
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class TestParkingLotController extends AutoRollBackTest {
	
	
	MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
	Map<String, String> paramsMap;
	

	public TestParkingLotController() {	
		
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
					.accept(MediaType.APPLICATION_FORM_URLENCODED)
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
					.accept(MediaType.APPLICATION_FORM_URLENCODED)
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
					.accept(MediaType.APPLICATION_FORM_URLENCODED)
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
