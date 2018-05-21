package com.park.ssm.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.csv.CSVParser;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.park.ParameterizedSpringTest;
import com.park.ssm.entity.ParkingLot;

public class TestParkingLotControllerAdd extends ParameterizedSpringTest {
	private MockMvc mockMvc=getMockMvc();
	
	@Parameters
	public static Collection<Map<String, Object>> data(){
		//CSVParser
		return null;
	}
	
	private ParkingLot data;
	public TestParkingLotControllerAdd(Object data) {
		this.data=(ParkingLot)data;
	}
	
	@Test
	public void test() {
		
	}
	
}
