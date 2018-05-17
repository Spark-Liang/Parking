package com.park.ssm.controller;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.web.servlet.MockMvc;

import com.park.ParameterizedSpringTest;

public class TestParkingLotController extends ParameterizedSpringTest {
	private MockMvc mockMvc=getMockMvc();
	
	@Parameters
	public static Collection<Object[]> data(){
		return new LinkedList<Object[]>(){
			{add(new Object[] {"data"});}
		};
	}
	
	private Object data;
	public TestParkingLotController(Object data) {
		this.data=data;
	}
	
	@Test
	public void test() {
		System.out.println(data);
	}
	
}
