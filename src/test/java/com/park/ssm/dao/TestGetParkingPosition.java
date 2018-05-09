package com.park.ssm.dao;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.park.BaseTest;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.ParkingPosition;
import java.util.*;

@Component
public class TestGetParkingPosition extends BaseTest{

	
	@Test
	public void testGet() {
		ParkingPositionDao dao=(ParkingPositionDao) super.getBean(ParkingPositionDao.class);
		//Set<ParkingPosition> obj=dao.loadParkingPositionByLotId(1);
		//ParkingLot parkingLot=(ParkingLot)(((ParkingPosition) obj.toArray()[0]).getParkingLot());
		ParkingPosition obj=dao.loadParkingPositionById(1);
		//ParkingLot parkingLot=obj.getParkingLot();
		//Set<ParkingPosition> set=parkingLot.getParkingPositions();
		//System.out.println(obj.containsAll(set));
		System.out.println(obj);
	}
	
	
}
