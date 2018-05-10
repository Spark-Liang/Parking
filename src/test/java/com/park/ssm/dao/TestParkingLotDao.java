package com.park.ssm.dao;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.BaseTest;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.type.ParkingLotState;

import junit.framework.Assert;

@Component
public class TestParkingLotDao extends BaseTest {
	@Autowired
	private ParkingLotDao dao;
	
	//@Test
	public void testLoadById() {
		ParkingLot parkingLot=dao.loadParkingLotById(1);
		System.out.println(parkingLot);
		System.out.println(Arrays.toString(parkingLot.getParkingPositions().toArray()));
	}
	
	@SuppressWarnings("serial")
	//@Test
	public void testListParkingLot() {
		Map<String, Object> conditions=new HashMap<>();
		conditions.put("name", "A");
		conditions.put("location", "C");
		conditions.put("totalPositionNum_max", Integer.MAX_VALUE);
		conditions.put("totalPositionNum_min", Integer.MIN_VALUE);
		conditions.put("cost_min",Double.MIN_VALUE);
		conditions.put("cost_max", Double.MAX_VALUE);
		conditions.put("price_min",Double.MIN_VALUE);
		conditions.put("price_max", Double.MAX_VALUE);
		conditions.put("states", new LinkedList<ParkingLotState>() {
			{add(ParkingLotState.ACTIVE);}
		});
		List<ParkingLot> list=dao.listParkingLot(conditions, 1, 3);
		for(ParkingLot tmp:list) {
			System.out.println(tmp);
		}
	}
	
	//@Test
	public void testUpdateParkingLot() {
		ParkingLot parkingLot=dao.loadParkingLotById(1);
		System.out.println(parkingLot);
		System.out.println(1==dao.updateParkingLot(parkingLot));
	}
	
	@Test
	public void testDeleteParkingLot() {
		ParkingLot parkingLot=dao.loadParkingLotById(1);
		System.out.println(parkingLot);
		System.out.println("delete");
		dao.deleteParkingLot(parkingLot.getId());
		parkingLot=dao.loadParkingLotById(1);
		Assert.assertEquals(parkingLot.getState(), ParkingLotState.INACTIVE);
		parkingLot.setState(ParkingLotState.ACTIVE);
		dao.updateParkingLot(parkingLot);
		System.out.println(parkingLot);
	}
	
	
}
