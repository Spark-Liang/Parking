package com.park.ssm.dao;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.type.AccountState;
import com.park.ssm.util.PersistentUtil;

import junit.framework.Assert;

@Component
public class TestAccountDao extends AutoRollBackTest {
	@Autowired
	private AccountDao dao;
	
	
	@Test
	public void tesAddAndtLoadById() {
		//test data
		Account account=new Account();
		account.setCardId(123L);
		account.setParkingLotId(123);
		account.setParkingPositionId(12345L);
		account.setUserId(123456L);
		dao.insertAccount(account);
		Assert.assertNotNull(account.getId());
		Account accountInDB=dao.loadAccountById(account.getId());
		assertEquals(account.getCardId(), accountInDB.getCardId());
		assertEquals(account.getParkingLotId(), accountInDB.getParkingLotId());
		assertEquals(account.getParkingPositionId(), accountInDB.getParkingPositionId());
		assertEquals(account.getUserId(), accountInDB.getUserId());
	}
	
	@Test
	public void testListById() {
		//test data
		Long userId=1L;
		
		List<Account> accounts=dao.findAccountrById(userId, null, null, null, null, null);
		Assert.assertNotNull(accounts);
		Assert.assertEquals(false, accounts.isEmpty());
	}
	
	@Test
	public void testUpdate() throws IllegalArgumentException, IllegalAccessException {
		//test data
		Long id=1L;
		
		Account accountInDB=dao.loadAccountById(id);
		Account account=new Account();
		PersistentUtil.merge(account, accountInDB);
		account.setState(AccountState.STOP);
		Map<String, Object> different=PersistentUtil.different(accountInDB, account);
		dao.modifyAccount(account.getId(),different);
		Account newAccountInDB=dao.loadAccountById(account.getId());
		assertEquals(newAccountInDB.getState(), AccountState.STOP);
	}
}
