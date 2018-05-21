package com.park.ssm.controller;

import java.io.*;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.PrintingResultHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.park.ParameterizedSpringTest;
import com.park.ssm.entity.ParkingLot;

//使用MockMvcRequestBuilders的静态方法生成mockrequest
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class TestParkingLotController extends ParameterizedSpringTest {
	

	@Parameters
	public static Collection<Object[]> data() throws IOException{
		//URL url=TestParkingLotController.class.getClassLoader().getResource("").;
		
		String fileUrl="com/park/ssm/controller/ParkingLotControllerTestData.csv";
		ClassPathResource resource=new ClassPathResource(fileUrl);
		Reader reader=new InputStreamReader(resource.getInputStream());
		CSVParser csvReader=new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
		Map<String,Integer> headerMap=csvReader.getHeaderMap();
		List<Object[]> data=new LinkedList<>();
		for(CSVRecord record:csvReader.getRecords()) {
			Map<String,	String> tmpParameterMap=new HashMap<>();
			for(String key:headerMap.keySet()) {
				tmpParameterMap.put(key, record.get(key));
			}
			data.add(new Object[] {tmpParameterMap});
		}
		csvReader.close();
		return data;
	}
	
	private MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
	private Map<String, String> paramsMap;
	
	@SuppressWarnings("unchecked")
	public TestParkingLotController(Object paramsMapData) {	
		paramsMap=(Map<String, String>)paramsMapData;
		for(String key:paramsMap.keySet()) {
			params.add(key, paramsMap.get(key));
		}
		
	}
	
	@Test
	public void test() throws Exception {
		System.out.println(JSON.toJSONString(paramsMap));
		ResultActions mockRequest=mockMvc.perform(
					post("/parkinglot/test")
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(JSON.toJSONString(paramsMap))
					);
		
		String content=mockRequest.andReturn().getResponse().getContentAsString();
		
		ParkingLot parkingLot=JSON.parseObject(content, ParkingLot.class);
		
		System.out.println(parkingLot);
		mockRequest.andExpect(status().isOk());
	}
	
}
