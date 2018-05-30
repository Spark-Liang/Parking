package com.park.ssm.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.park.AutoRollBackTest;
import com.park.ssm.entity.InnerUser;
import com.park.ssm.util.Encryption;

public class TestInnerUserService extends AutoRollBackTest {
	@Autowired
	private InnerUserService innerUserService;

	@Test
	// @Rollback(false)
	public void testInsertInnerUser() {
		InnerUser innerUser = new InnerUser();
		innerUser.setNickname("14785236930");
		innerUser.setPassword("123678");
		innerUser.setTypeflag(2);
		innerUser.setName("Mei");
		Assert.assertTrue(innerUserService.insertInnerUser(innerUser) > 0);
	}

	@Test
	//@Rollback(false)
	public void testChangeInnerUserByNickname() {
		InnerUser innerUser = new InnerUser();
		innerUser.setId(2);
		innerUser.setNickname("10987654321");
		innerUser.setPassword("9876/-74321");
		innerUser.setTypeflag(1);
		// innerUser.setName("Messi");

		// System.out.println("----------------------------------"+innerUser.getAddress());
		Assert.assertTrue(innerUserService.changeInnerUserByNickname(innerUser) > 0);
	}

	@Test
	public void testFindInnerUserByNickname() {
		InnerUser innerUser = innerUserService.findInnerUserByNickname("15219326102");
		Assert.assertEquals("ff", innerUser.getName());
	}

	@Test
	public void testFindInnerUser() {
		InnerUser innerUser = innerUserService.findInnerUser("13075119722", "123456");
		Assert.assertEquals(2, innerUser.getTypeflag());
	}

	@Test
	// @Rollback(false)
	public void testdropInnerUserByNickname() {
		Assert.assertTrue(innerUserService.dropInnerUserByNickname("10987654321") > 0);
	}

	@Test
	public void testFindInnerUserByTypeflag() {
		List<InnerUser> list = new ArrayList<InnerUser>();
		list = innerUserService.findInnerUserByTypeflag();
		Assert.assertEquals("13075119722", list.get(0).getNickname());
	}

	@Test
	public void testFindSaltByNickname() {
		String salt = innerUserService.findSaltByNickname("123");
		Assert.assertEquals("123", salt);
	}
	
	/**
	 * 增加admin的测试用例方法
	 * 去掉@Rollback(false)的注释即可写入到MySQL
	 * setNickname中的号码不能重复
	 */
	@Test
	//@Rollback(false)
	public void InsertSalt() {
		InnerUser innerUser = new InnerUser();
		innerUser.setNickname("13745678910");
		innerUser.setPassword("123456");
		innerUser.setTypeflag(0);
		innerUser.setName("Hacker");
		String password = innerUser.getPassword();
		Encryption en = new Encryption();
		String salt = en.createSalt();
		password += salt;
		password = en.SHA512(password);
		innerUser.setPassword(password);
		innerUser.setSalt(salt);
		Assert.assertTrue(innerUserService.insertInnerUser(innerUser) > 0);
	}
}
