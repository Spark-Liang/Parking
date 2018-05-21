package com.park.ssm.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.park.AutoRollBackTest;
import com.park.BaseTest;
import com.park.ParameterizedSpringTest;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.service.ParkingLotService;
import com.park.ssm.service.impl.ParkingLotServiceImpl;

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
	
}
