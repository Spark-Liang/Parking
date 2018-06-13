package com.park.ssm.service;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;

@Component
public class TestAccountStateLogService extends AutoRollBackTest{
	@Autowired
	private AccountStateLogService service;
	
	@Test
	public void testGetBillpropBeforeTerminate() {
		//test data
		Long accountId=23L;
		
		Map<String, Object>billProps=service.getBillPropBeforeTerminated(accountId);
		System.out.println(billProps);
	}
	
}
