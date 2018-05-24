package com.park.ssm.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.park.AutoLoginTest;

//使用MockMvcRequestBuilders的静态方法生成mockrequest
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestExceptionHandler extends AutoLoginTest{
	
	@Test
	public void testThrowException() throws Exception {
		//test data
		String testUrl="/throw";
		
		ResultActions request=mockMvc.perform(
									get(testUrl)
									.session(session)
									.contentType(MediaType.APPLICATION_FORM_URLENCODED)
									.accept(MediaType.APPLICATION_JSON_UTF8)
									);
		MockMvcResultHandlers.print().handle(request.andReturn());
		
	}
	
}
