package com.park.ssm.dao;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.Account;

import junit.framework.Assert;

@Component
public class TestAccountDao extends AutoRollBackTest {
	@Autowired
	private AccountDao dao;
	
	
	@Test
	public void testLoadById() {
		//test data
		Long id=1L;
		
		Account account=dao.loadAccountById(id);
		
		Assert.assertNotNull(account);
		System.out.println(account);
	}
	
	@Test
	public void testListById() {
		//test data
		Long userId=1L;
		
		List<Account> accounts=dao.findAccountrById(userId, null, null, null, null, null);
		Assert.assertNotNull(accounts);
		Assert.assertEquals(false, accounts.isEmpty());
	}
}
