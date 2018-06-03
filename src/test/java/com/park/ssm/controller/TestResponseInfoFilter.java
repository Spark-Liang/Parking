package com.park.ssm.controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.park.AutoRollBackTest;
import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.entity.InnerUser;
import com.park.ssm.entity.ParkingLot;

@Component
public class TestResponseInfoFilter extends AutoRollBackTest {
	@Autowired
	private ParkingLotDao parkingLotDao;

	//@Test
	public void testInfoFilter() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		// test data
		InnerUser innerUser = new InnerUser();
		innerUser.setId(123);
		innerUser.setTypeflag(0);

		ResponseInfoFilter filter = new ResponseInfoFilter();
		Class<ResponseInfoFilter> clazz = ResponseInfoFilter.class;
		Field innerUserField = clazz.getDeclaredField("innerUser");
		innerUserField.setAccessible(true);
		innerUserField.set(filter, innerUser);

		Method filterMethod = clazz.getDeclaredMethod("filterJSONInfo", Object.class);
		filterMethod.setAccessible(true);
		List<ParkingLot> parkingLots = parkingLotDao.listParkingLot(null, null, null);

		System.out.println(filterMethod.invoke(filter, parkingLots));

	}

	//@Test
	public void testWithOutPermission() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		ResponseInfoFilter filter = new ResponseInfoFilter();
		Class<ResponseInfoFilter> clazz = ResponseInfoFilter.class;


		Method filterMethod = clazz.getDeclaredMethod("filterJSONInfo", Object.class);
		filterMethod.setAccessible(true);
		List<ParkingLot> parkingLots = parkingLotDao.listParkingLot(null, null, null);

		try {
			System.out.println(filterMethod.invoke(filter, parkingLots));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFilterClass() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		Class<ResponseInfoFilter> clazz=ResponseInfoFilter.class;
		Field field=clazz.getDeclaredField("InfoControllerClassSet");
		field.setAccessible(true);
		Object value=field.get(clazz.newInstance());
		System.out.println(value);
		
	}
}
