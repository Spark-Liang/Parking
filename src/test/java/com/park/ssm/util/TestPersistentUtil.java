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
		ParkingLot parkingLotTarget=new ParkingLot();
		PersistentUtil.merge(parkingLotTarget, parkingLot);
		
		Assert.assertEquals(parkingLotTarget.getId(), parkingLot.getId());
		Assert.assertEquals(parkingLotTarget.getCurrentPrice(), parkingLot.getCurrentPrice());
		Assert.assertEquals(parkingLotTarget.getLocation(), parkingLot.getLocation());
		Assert.assertEquals(parkingLotTarget.getName(), parkingLot.getName());
		Assert.assertEquals(parkingLotTarget.getTotalPositionNum(), parkingLot.getTotalPositionNum());
		Assert.assertEquals(parkingLotTarget.getState(), parkingLot.getState());
	}
	
	@Test
	public void testDifferent() throws IllegalArgumentException, IllegalAccessException {
		//test data
		double cost=111,currentPrice=999;
		
		ParkingLot parkingLotTarget=new ParkingLot();
		PersistentUtil.merge(parkingLotTarget, parkingLot);
		
		parkingLot.setCost(cost);
		parkingLot.setCurrentPrice(currentPrice);
		
		Map<String, Object> different= PersistentUtil.different(parkingLotTarget, parkingLot);
		
		Assert.assertNotNull(different.get("cost"));
		Assert.assertNotNull(different.get("currentPrice"));
		
		Assert.assertEquals(different.get("cost"), cost);
		Assert.assertEquals(different.get("currentPrice"), currentPrice);
		Assert.assertEquals(different.keySet().size(), 2);
	}
	
}
