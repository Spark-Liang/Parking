package com.park.ssm.dao;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.type.ParkingLotState;
import com.park.ssm.util.PersistentUtil;

import static junit.framework.Assert.*;

@Component
public class TestParkingLotDao extends AutoRollBackTest {
	@Autowired
	private ParkingLotDao dao;
	
	@Test
	public void testLoadById() {
		ParkingLot parkingLot=dao.loadParkingLotById(1);
		System.out.println(parkingLot);
		System.out.println(Arrays.toString(parkingLot.getParkingPositions().toArray()));
	}
	
	@SuppressWarnings("serial")
	@Test
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
	
	@Test
	public void testUpdateParkingLot() throws IllegalArgumentException, IllegalAccessException {
		//测试变量
		double cost=111.0,currentPrice=123;
		Integer id=1;
		
		ParkingLot parkingLotInDB=dao.loadParkingLotById(id);
		ParkingLot parkingLot=new ParkingLot();
		PersistentUtil.merge(parkingLot, parkingLotInDB);
		
		
		parkingLot.setCost(cost);
		parkingLot.setCurrentPrice(currentPrice);
		
		Map<String, Object> different=PersistentUtil.different(parkingLotInDB, parkingLot);
		different.put("id", id);
		assertEquals(different.isEmpty(), false);
		assertEquals(1,dao.updateParkingLot(different));
		parkingLotInDB=dao.loadParkingLotById(id);
		assertEquals(cost, parkingLotInDB.getCost());
		assertEquals(currentPrice, parkingLotInDB.getCurrentPrice());
		
		
	}
	
	@Test
	public void testDeleteParkingLot() {
		//测试变量
		Integer id=1;
		
		ParkingLot parkingLotInDB=dao.loadParkingLotById(id);
		System.out.println(parkingLotInDB);
		System.out.println("delete");
		dao.deleteParkingLot(parkingLotInDB.getId());
		parkingLotInDB=dao.loadParkingLotById(id);
		assertEquals(parkingLotInDB.getState(), ParkingLotState.INACTIVE);
		
	}
	
	
}
