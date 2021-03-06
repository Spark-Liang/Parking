package com.park.ssm.dao;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

import com.park.AutoRollBackTest;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.type.AccountState;
import com.park.ssm.service.ParkingLotService;
import com.park.ssm.util.Encryption;
import com.park.ssm.util.PersistentUtil;

import junit.framework.Assert;

@Component
public class TestAccountDao extends AutoRollBackTest {
	@Autowired
	private AccountDao dao;
	
	@Autowired
	private ParkingPositionDao parkingpositiondao;
	
	@Autowired
	private UserDao userdao;
	
//	@Test
	/*public void tesAddAndtLoadById() {
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
	}*/
	
//	@Test
	public void testListById() {
		//test data
		Long userId=13745678911L;
		
		List<Account> accounts=dao.findAccountrById(userId, null, null, null, null, null);
		Assert.assertNotNull(accounts);
		Assert.assertEquals(false, accounts.isEmpty());
	}
	
//	@Test
	public void testUpdate() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		//test data
		Long id=1L;
		
		Account accountInDB=dao.loadAccountById(id);
		Account account=new Account();
		PersistentUtil.<Account>merge(account, accountInDB,Account.class);
		Field stateField=Account.class.getDeclaredField("state");
		boolean accessible=stateField.isAccessible();
		stateField.setAccessible(true);
		stateField.set(account, AccountState.STOP);
		stateField.setAccessible(accessible);
		Map<String, Object> different=PersistentUtil.different(accountInDB, account,Account.class);
		dao.modifyAccount(account.getId(),different);
		Account newAccountInDB=dao.loadAccountById(account.getId());
		assertEquals(newAccountInDB.getState(), AccountState.STOP);
	}

//	@Test
//	@Rollback(true)
	public void testAddNewCard() {//测试增加新停车卡
		//添加能够停车的停车场
		ParkingLotService parkingLotService=applicationContext.getBean(ParkingLotService.class);
		ParkingLot parkingLot=new ParkingLot();
		parkingLot.setName("Test Add New Card"+System.currentTimeMillis());
		parkingLot.setTotalPositionNum(10);
		parkingLot.setCost(100.0);
		parkingLotService.saveParkingLot(parkingLot);
		
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("parkingLotId", parkingLot.getId());
		paramMap.put("cardId", System.currentTimeMillis() % 100000000000L);
		paramMap.put("userId", "13745678910");
		dao.addNewCard(paramMap);
		Integer flag=(int) paramMap.get("flag");
		assertEquals(flag, new Integer(0));
		Account account=dao.loadAccountById((Long)paramMap.get("accountId"));
		Assert.assertNotNull(account);
		ParkingPositionDao parkingPositionDao=applicationContext.getBean(ParkingPositionDao.class);
		Assert.assertNotNull(parkingPositionDao.loadParkingPositionById(account.getParkingPositionId()));
		//int num=parkingpositiondao.getPositionNumByUser(id,1);
		//System.out.println("数量为"+num);
	}
	
//	@Test
	public void testchangeNewCard() throws IllegalArgumentException, IllegalAccessException {//测试更换停车卡
		long cardId=13745678911l;
		Account account=dao.getCardMessage(cardId);
		Assert.assertNotNull(account);
		System.out.println("停车场编号为："+account.getParkingLot()+"停车位编号为："+account.getParkingPositionId());
		Account accountInDB=new Account();
		PersistentUtil.<Account>merge(account, accountInDB,Account.class);
		account.setCardId(100l);
		Map<String, Object> different=PersistentUtil.different(accountInDB, account, Account.class);
		int result=dao.modifyAccount(account.getId(),different);
		Assert.assertEquals(1, result);
		Account newAccount=dao.loadAccountById(account.getId());
		assertEquals(account.getCardId(),newAccount.getCardId());
//		System.out.println("数据库更新条数为："+result);
//		System.out.println("新的停车卡号："+account.getCardId());
	}
	
	
	//@Test
	public void testDate() {//测试日期是否相等
		String times = new SimpleDateFormat("MMdd").format(new Date());
		String exetime = "0529";
		System.out.println(times.equals(exetime));
	}
//	@Test
	public void testgetAllcard() {//测试获取所有的card
		long userId=13745678910l;
		List<Account> list=dao.findAccountrById(userId,null,null,null,null,null);
	}
//	@Test
	public void testgetCardMessage() {
		long cardId=1l;
		Account account=dao.getCardMessage(cardId);
		AccountState state=account.getState();
		System.out.println(state.getInd());
	}
	
//	@Test
	public void testAddUser() {
		long userId=18826237366l;
		String password="123456";
		Encryption en=new Encryption();
		String salt=en.createSalt();
		String passwordAndSalt = en.SHA512(password.trim() + salt);
		int a=userdao.insertUser(userId, passwordAndSalt, salt);
		System.out.println(a);
	}
	
	@Test
	public void testStopCard() {
		long cardId=123l;
		AccountState state=AccountState.getValueByInd(-2);
		int status=dao.updateCardStatus(cardId,state);
		System.out.println("状态为： "+status);
	}

}
