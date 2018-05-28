package com.park.ssm.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.park.AutoLoginTest;
import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.dao.ParkingPositionDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.ParkingPosition;
import com.park.ssm.service.ParkingLotService;

import junit.framework.Assert;

//使用MockMvcRequestBuilders的静态方法生成mockrequest
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

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
	
	
	//@Test
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
	
	//@Test
	public void testList() throws Exception {
		MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
		ResultActions mockRequest=mockMvc.perform(
					post("/parkinglot/list")
					.session(session)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.params(params)
					);
		MockMvcResultHandlers.print(System.out).handle(mockRequest.andReturn());
		mockRequest.andExpect(status().isOk());
		
		
		
		ParkingLotDao dao=applicationContext.getBean(ParkingLotDao.class);
		
	}

	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdate() throws Exception {
		//测试参数
		//添加测试parkingLot
		ParkingLot parkingLot=new ParkingLot(null, 100);
		parkingLot.setCost(123.0);
		parkingLot.setCurrentPrice(111);
		parkingLot.setLocation("TEST LOCATION");
		parkingLot.setName("TEST NAME");
		
		ParkingLotDao parkingLotDao=applicationContext.getBean(ParkingLotDao.class);
		parkingLotDao.insertParkingLot(parkingLot);
		
		//更新操作
		MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
		parkingLot.setCost(parkingLot.getCost()+1);
		parkingLot.setLocation(parkingLot.getLocation()+1);
		parkingLot.setCurrentPrice(parkingLot.getCurrentPrice()+1);
		for(Field field:parkingLot.getClass().getDeclaredFields()) {
			boolean accessible=field.isAccessible();
			field.setAccessible(true);
			Object tmpValue=field.get(parkingLot);
			params.add(field.getName(), tmpValue!=null?tmpValue.toString():null);
			field.setAccessible(accessible);
		}
		
		ResultActions mockRequest=mockMvc.perform(
					post("/parkinglot/update")
					.session(session)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.params(params)
					);
		MvcResult result=mockRequest.andReturn();
		String responseContent=result.getResponse().getContentAsString();
		
		MockMvcResultHandlers.print(System.out).handle(mockRequest.andReturn());
		//测试正常返回
		mockRequest.andExpect(status().isOk());
		JSON resObj=JSON.parseObject(responseContent);
		Map<String, Object> responseResult=new HashMap<>();
		responseResult.put("res", true);
		responseResult.put("parkingLot", parkingLot);
		resObj.equals(JSON.parseObject(JSON.toJSONString(responseResult)));
		Assert.assertEquals(resObj,JSON.parseObject(JSON.toJSONString(responseResult)));
	}
	
	//@Test
	public void testDelete() throws Exception {
		
		//测试初始化
		Integer id=testDeleteBefore();
		
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
					//.accept(MediaType.APPLICATION_JSON_UTF8)
					.params(params)
					);
		MockMvcResultHandlers.print(System.out).handle(mockRequest.andReturn());
		mockRequest.andExpect(status().isOk());
		Map<String,Object> resultmap=new HashMap<>();
		resultmap.put("res", true);
		
		
		
	}
	private Integer testDeleteBefore() {
		int test_totNum = 100;
		Integer id=null;
		ParkingLot parkingLot = new ParkingLot(null, test_totNum);

		parkingLot.setName("TEST");

		parkingLot.setCost(22.0);
		parkingLot.setCurrentPrice(33.0);
		parkingLot.setLocation("LOCAL");
		
		ParkingLotService service=applicationContext.getBean(ParkingLotService.class);
		service.saveParkingLot(parkingLot);
		Assert.assertNotNull(parkingLot.getId());
		id=parkingLot.getId();
		
		ParkingPositionDao parkingPositionDao=applicationContext.getBean(ParkingPositionDao.class);
		List<ParkingPosition> parkingPositions = parkingPositionDao.loadParkingPositionByLotId(parkingLot.getId());
		Assert.assertEquals(test_totNum, parkingPositions.size());
		return id;
	}
	
}
