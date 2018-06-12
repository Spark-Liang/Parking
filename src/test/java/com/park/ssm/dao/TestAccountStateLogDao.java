package com.park.ssm.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.AccountStateLog;
@Component
public class TestAccountStateLogDao extends AutoRollBackTest{
	@Autowired
	private AccountStateLogDao dao;
	
	@Test
	public void testListLog() {
		List<AccountStateLog> accountStateLogs=dao.listLogByAccountId(1L, null, null, null, null, null, null);
		System.out.println(accountStateLogs);
	}
}
