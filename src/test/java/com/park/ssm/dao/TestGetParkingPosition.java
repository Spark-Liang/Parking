package com.park.ssm.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.ParkingPosition;

@Component
public class TestGetParkingPosition extends AutoRollBackTest{

	@Autowired
	ParkingPositionDao dao;
	
	@Test
	public void testGet() {
		ParkingPosition obj=dao.loadParkingPositionById(1);
		//ParkingLot parkingLot=obj.getParkingLot();
		//Set<ParkingPosition> set=parkingLot.getParkingPositions();
		//System.out.println(obj.containsAll(set));
		System.err.println(obj);
	}
	
	
}
