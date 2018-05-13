package com.park.ssm.service;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.park.AutoRollBackTest;
import com.park.ssm.dao.ParkingPositionDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.ParkingPosition;

import junit.framework.Assert;

@Component
public class TestParkingLotService extends AutoRollBackTest{
	@Autowired
	private ParkingLotService service;
	@Autowired
	private ParkingPositionDao parkingPositionDao;
	
	@Test
	public void  testsaveParkingLot() {
		
		int test_totNum=100;
		
		ParkingLot parkingLot=new ParkingLot(null,test_totNum);
		
		parkingLot.setName("TEST");
		
		parkingLot.setCost(22.0);
		parkingLot.setCurrentPrice(33.0);
		parkingLot.setLocation("LOCAL");
		
		service.saveParkingLot(parkingLot);
		Assert.assertNotNull(parkingLot.getId());
		
		Set<ParkingPosition> parkingPositions=parkingPositionDao.loadParkingPositionByLotId(parkingLot.getId());
		Assert.assertEquals(test_totNum, parkingPositions.size());
		
		throw new RuntimeException("test");
	}
}
