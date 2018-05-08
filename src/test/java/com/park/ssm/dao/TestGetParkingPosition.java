package com.park.ssm.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.BaseTest;
import com.park.ssm.entity.ParkingPosition;

@Component
public class TestGetParkingPosition extends BaseTest{
	@Autowired
	private ParkingPositionDao dao;
	
	@Test
	public void testGet() {
		UserDao dao=(UserDao) super.getBean(UserDao.class);
		//ParkingPosition position=;
		System.out.println(dao.loadUserById(1));
	}
}
