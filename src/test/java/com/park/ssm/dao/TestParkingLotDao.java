package com.park.ssm.dao;

import java.util.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.park.AutoRollBackTest;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.type.ParkingLotState;

import junit.framework.Assert;

@Component
public class TestParkingLotDao extends AutoRollBackTest {
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
		conditions.put("totalPositionNum_max", Integer.MAX_VALUE);
		conditions.put("totalPositionNum_min", Integer.MIN_VALUE);
		conditions.put("cost_min",Double.MIN_VALUE);
		conditions.put("cost_max", Double.MAX_VALUE);
		conditions.put("price_min",Double.MIN_VALUE);
		conditions.put("price_max", Double.MAX_VALUE);
		conditions.put("states", new LinkedList<ParkingLotState>() {
			{add(ParkingLotState.ACTIVE);}
		});
		List<ParkingLot> list=dao.listParkingLot(conditions, 1, Integer.MAX_VALUE);
		for(ParkingLot tmp:list) {
			System.out.println(tmp);
		}
		Assert.assertEquals(list.size(), dao.countParkingLot(conditions));
		
	}
	
	//@Test
	public void testUpdateParkingLot() {
		ParkingLot parkingLot=dao.loadParkingLotById(1);
		System.out.println(parkingLot);
		System.out.println(1==dao.updateParkingLot(parkingLot));
	}
	
	//@Test
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
	
	//@Test
	public void testUpdateFail() {
		ParkingLot parkingLot=new ParkingLot(10, 200);
		dao.updateParkingLot(parkingLot);
	}
	
	@Test
	public void testJSON() {
		ParkingLot parkingLot=dao.loadParkingLotById(1);
		String jsonString=JSON.toJSONString(parkingLot);
		System.out.println(jsonString);
		ParkingLot reverse=JSON.parseObject(jsonString, ParkingLot.class);
		System.out.println(reverse);
	}
}
