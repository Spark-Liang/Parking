package com.park.ssm.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.User;
import com.park.ssm.util.Encryption;

public class TestUserService extends AutoRollBackTest{
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindUser() {
		Encryption en=new Encryption();
		String salt=userService.findSaltByUserId(Long.valueOf(12345678919L));
		String password=en.SHA512("123456"+salt);
		User user=userService.findUser(Long.valueOf(12345678919L), password);
		Assert.assertEquals(Long.valueOf(12345678919L), user.getUserId());
	}
	
	@Test
	public void testFindUserById() {
		User user=userService.findUserById(Long.valueOf(12345678919L));
		Assert.assertEquals(Long.valueOf(12345678919L), user.getUserId());
	}
	
	@Test
	public void testFindSaltByUserId() {
		String salt=userService.findSaltByUserId(Long.valueOf(12345678919L));
		System.out.println("-----------");
		Assert.assertEquals(null, salt);
	}
}
