package com.park.ssm.dao;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import java.text.SimpleDateFormat;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.type.AccountState;
import com.park.ssm.util.PersistentUtil;

import junit.framework.Assert;

@Component
public class TestAccountDao extends AutoRollBackTest {
	@Autowired
	private AccountDao dao;
	
	
//	@Test
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
	
//	@Test
	public void testListById() {
		//test data
		Long userId=1L;
		
		List<Account> accounts=dao.findAccountrById(userId, null, null, null, null, null);
		Assert.assertNotNull(accounts);
		Assert.assertEquals(false, accounts.isEmpty());
	}
	
//	@Test
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

//	@Test
	public void testAddNewCard() {//测试增加新停车卡
		Account account=new Account();
		long userId=18826237365l;
		account.setUserId(userId);
		account.setParkingLotId(1);
		account.setCardId(123456789l);
		account.setState(AccountState.NORMAL);
		int falg=0;
		falg=dao.insertAccount(account);
		System.out.println(falg);
	}
	
//	@Test
	public void testchangeNewCard() {//测试更换停车卡
		long cardId=1l;
		Account account=dao.getCardMessage(cardId);
		System.out.println("停车场编号为："+account.getParkingLotId()+"停车位编号为："+account.getParkingPositionId());
		account.setCardId(100l);
//		int result=dao.modifyAccount(account);
//		System.out.println("数据库更新条数为："+result);
//		System.out.println("新的停车卡号："+account.getCardId());
	}
	
	
	@Test
	public void testDate() {//测试日期是否相等
		String times = new SimpleDateFormat("MMdd").format(new Date());
		String exetime = "0529";
		System.out.println(times.equals(exetime));
	}

}
