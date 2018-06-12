package com.park.ssm.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.Account;

@Component
public class TestAccountService extends AutoRollBackTest{
	@Autowired
	private AccountService accountService;
	
	@Test
	public void testListAccount() {
		List<Account> accounts=accountService.findAccountrById(13745678910L, null, null, null, null, null, false);
		System.out.println(accounts);
		accounts=accountService.findAccountrById(13745678910L, null, null, null, null, null, true);
		System.out.println(accounts);
	}
}
