package com.park.ssm.util;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.type.ParkingLotState;

import junit.framework.Assert;


@Component
public class TestPersistentUtil extends AutoRollBackTest{
	
	private ParkingLot parkingLot;
	
	@Before
	public void setTestObject(){
		parkingLot=new ParkingLot(123, 100);
		
		parkingLot.setCurrentPrice(1234);
		parkingLot.setCost(123.0);
		parkingLot.setName("ABC");
		parkingLot.setLocation("AAA");
		parkingLot.setState(ParkingLotState.ACTIVE);
	}
	
	@Test
	public void testMerge() throws IllegalArgumentException, IllegalAccessException {
		//test data
		ParkingLot parkingLotOld=new ParkingLot();
		PersistentUtil.merge(parkingLotOld, parkingLot,ParkingLot.class);
		
		Assert.assertEquals(parkingLotOld.getId(), parkingLot.getId());
		Assert.assertEquals(parkingLotOld.getCurrentPrice(), parkingLot.getCurrentPrice());
		Assert.assertEquals(parkingLotOld.getLocation(), parkingLot.getLocation());
		Assert.assertEquals(parkingLotOld.getName(), parkingLot.getName());
		Assert.assertEquals(parkingLotOld.getTotalPositionNum(), parkingLot.getTotalPositionNum());
		Assert.assertEquals(parkingLotOld.getState(), parkingLot.getState());
	}
	
	@Test
	public void testDifferent() throws IllegalArgumentException, IllegalAccessException {
		//test data
		double cost=111,currentPrice=999;
		
		ParkingLot parkingLotOld=new ParkingLot();
		PersistentUtil.merge(parkingLotOld, parkingLot,ParkingLot.class);
		
		parkingLot.setCost(cost);
		parkingLot.setCurrentPrice(currentPrice);
		
		Map<String, Object> different= PersistentUtil.different(parkingLotOld, parkingLot,ParkingLot.class);
		
		Assert.assertNotNull(different.get("cost"));
		Assert.assertNotNull(different.get("currentPrice"));
		
		Assert.assertEquals(different.get("cost"), cost);
		Assert.assertEquals(different.get("currentPrice"), currentPrice);
		Assert.assertEquals(different.keySet().size(), 2);
	}
	
}
