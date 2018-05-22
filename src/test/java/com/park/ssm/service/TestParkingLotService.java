package com.park.ssm.service;

import java.sql.SQLException;
import java.util.Set;

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
	public void testsaveParkingLot() throws SQLException {

		int test_totNum = 100;

		ParkingLot parkingLot = new ParkingLot(null, test_totNum);

		parkingLot.setName("TEST");

		parkingLot.setCost(22.0);
		parkingLot.setCurrentPrice(33.0);
		parkingLot.setLocation("LOCAL");

		service.saveParkingLot(parkingLot);
		Assert.assertNotNull(parkingLot.getId());

		Set<ParkingPosition> parkingPositions = parkingPositionDao.loadParkingPositionByLotId(parkingLot.getId());
		Assert.assertEquals(test_totNum, parkingPositions.size());

		// throw new RuntimeException("test");
	}

	@Test
	public void testsaveParkingLotSameTest() throws SQLException {

		int test_totNum = 100;

		ParkingLot parkingLot = new ParkingLot(null, test_totNum);

		parkingLot.setName("TEST");

		parkingLot.setCost(22.0);
		parkingLot.setCurrentPrice(33.0);
		parkingLot.setLocation("LOCAL");

		service.saveParkingLot(parkingLot);
		Assert.assertNotNull(parkingLot.getId());

		Set<ParkingPosition> parkingPositions = parkingPositionDao.loadParkingPositionByLotId(parkingLot.getId());
		Assert.assertEquals(test_totNum, parkingPositions.size());

		// throw new RuntimeException("test");
	}

	// @Test
	public void saveSameName() {
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

		try {
			service.saveParkingLot(parkingLot1);
			service.saveParkingLot(parkingLot2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void deleteParkingLot() throws SQLException {
		//Test data
		Integer id=1;
		
		ParkingLot parkingLot=applicationContext.getBean(ParkingLotDao.class).loadParkingLotById(id);
		Assert.assertEquals(parkingLot.getState(), ParkingLotState.ACTIVE);
		
		service.deleteParkingLot(parkingLot.getId());
		
		parkingLot=applicationContext.getBean(ParkingLotDao.class).loadParkingLotById(id);
		
		Assert.assertEquals(parkingLot.getState(), ParkingLotState.INACTIVE);
	}
}
