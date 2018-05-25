package com.park.ssm.service;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.park.AutoRollBackTest;
import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.dao.ParkingPositionDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.ParkingPosition;
import com.park.ssm.entity.type.ParkingLotState;

import junit.framework.Assert;

@Component
public class TestParkingLotService extends AutoRollBackTest {
	@Autowired
	private ParkingLotService service;
	@Autowired
	private ParkingPositionDao parkingPositionDao;

	@Test
	public void testsaveParkingLot(){

		int test_totNum = 100;

		ParkingLot parkingLot = new ParkingLot(null, test_totNum);

		parkingLot.setName("TEST");

		parkingLot.setCost(22.0);
		parkingLot.setCurrentPrice(33.0);
		parkingLot.setLocation("LOCAL");

		service.saveParkingLot(parkingLot);
		Assert.assertNotNull(parkingLot.getId());

		List<ParkingPosition> parkingPositions = parkingPositionDao.loadParkingPositionByLotId(parkingLot.getId());
		Assert.assertEquals(test_totNum, parkingPositions.size());

		// throw new RuntimeException("test");
	}

	@Test
	public void testsaveParkingLotSameTest() {

		int test_totNum = 100;

		ParkingLot parkingLot = new ParkingLot(null, test_totNum);

		parkingLot.setName("TEST");

		parkingLot.setCost(22.0);
		parkingLot.setCurrentPrice(33.0);
		parkingLot.setLocation("LOCAL");

		service.saveParkingLot(parkingLot);
		Assert.assertNotNull(parkingLot.getId());

		List<ParkingPosition> parkingPositions = parkingPositionDao.loadParkingPositionByLotId(parkingLot.getId());
		Assert.assertEquals(test_totNum, parkingPositions.size());

		// throw new RuntimeException("test");
	}

	@Test(expected=RuntimeException.class)
	public void saveSameName() {
		//test data
		int test_totNum = 100;

		ParkingLot parkingLot = new ParkingLot(null, test_totNum);

		parkingLot.setName("TEST");

		parkingLot.setCost(22.0);
		parkingLot.setCurrentPrice(33.0);
		parkingLot.setLocation("LOCAL");

		service.saveParkingLot(parkingLot);
		Assert.assertNotNull(parkingLot.getId());

		List<ParkingPosition> parkingPositions = parkingPositionDao.loadParkingPositionByLotId(parkingLot.getId());
		Assert.assertEquals(test_totNum, parkingPositions.size());

		
		ParkingLot parkingLot1 = new ParkingLot(null, 120), parkingLot2 = new ParkingLot(null, 100);
		String name = "test_SameName";

		parkingLot1.setName(name);
		parkingLot1.setCost(11.0);
		parkingLot1.setLocation("locaitonA");
		parkingLot1.setCurrentPrice(33);

		parkingLot2.setName(name);
		parkingLot2.setCost(22.0);
		parkingLot2.setLocation("locaitonB");
		parkingLot2.setCurrentPrice(44);

		service.saveParkingLot(parkingLot1);
		service.saveParkingLot(parkingLot2);
	}

	@Test
	public void deleteParkingLot() {
		// Test data
		Integer id = null;

		int test_totNum = 100;

		ParkingLot parkingLot = new ParkingLot(null, test_totNum);

		parkingLot.setName("TEST");

		parkingLot.setCost(22.0);
		parkingLot.setCurrentPrice(33.0);
		parkingLot.setLocation("LOCAL");

		service.saveParkingLot(parkingLot);
		Assert.assertNotNull(parkingLot.getId());
		id=parkingLot.getId();
		
		List<ParkingPosition> parkingPositions = parkingPositionDao.loadParkingPositionByLotId(parkingLot.getId());
		Assert.assertEquals(test_totNum, parkingPositions.size());

		parkingLot = applicationContext.getBean(ParkingLotDao.class).loadParkingLotById(id);
		
		Assert.assertEquals(parkingLot.getState(), ParkingLotState.ACTIVE);

		service.deleteParkingLot(parkingLot.getId());

		parkingLot = applicationContext.getBean(ParkingLotDao.class).loadParkingLotById(id);

		Assert.assertEquals(parkingLot.getState(), ParkingLotState.INACTIVE);
	}

	@Test(expected= RuntimeException.class)
	public void testDeleteFail() {
		// Test data
		Integer id = 1;
		
		
		ParkingLot parkingLot = applicationContext.getBean(ParkingLotDao.class).loadParkingLotById(id);
		
		Assert.assertEquals(parkingLot.getState(), ParkingLotState.ACTIVE);
		for(ParkingPosition parkingPosition:parkingLot.getParkingPositions()) {
			if(parkingPosition.getAccountId()!=null) {
				parkingPosition.setAccountId(new Long(1));
				break;
			}
		}
		
		service.deleteParkingLot(parkingLot.getId());
		
		parkingLot = applicationContext.getBean(ParkingLotDao.class).loadParkingLotById(id);

		Assert.assertEquals(parkingLot.getState(), ParkingLotState.INACTIVE);

	}

}
