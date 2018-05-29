package com.park.ssm.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.park.AutoRollBackTest;

//使用MockMvcRequestBuilders的静态方法生成mockrequest
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestParkTerminal extends AutoRollBackTest {
	
	@Test
	public void testPark() throws Exception {
		//test data
		MultiValueMap<String,String> params=new LinkedMultiValueMap<String, String>();
		params.add("parkingLotId", "1");
		params.add("cardId", "13745678911");
		
		ResultActions resultActions=mockMvc.perform(post("/parkTerminal/park")
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.params(params));
		MockMvcResultHandlers.print(System.out).handle(resultActions.andReturn());
	}
}
