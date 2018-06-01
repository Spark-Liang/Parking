package com.park;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.ParkingPosition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:configs/spring/*xml"})
@WebAppConfiguration
public class BaseTest implements ApplicationContextAware{
	
	private ApplicationContext context;
	//@Test
	public void test() {
		System.out.println("hello");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.context=arg0;
	}
	
	public ApplicationContext getContext() {
		return context;
	}
	

	public Object getBean(Class<?> clazz) {
		return context.getBean(clazz);
	}
	
	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	

	@Test
	public void testObjectToMap() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ParkingLotDao dao=context.getBean(ParkingLotDao.class);
		List<ParkingLot> parkingLots=dao.listParkingLot(null, null, null);
		List<Map<Object, Object>> resultList=new LinkedList<>();
		for(ParkingLot parkingLot:parkingLots) {
			System.out.println(parkingLot.hashCode());
			Method hashCode=Object.class.getMethod("hashCode", null);
			System.out.println(hashCode.invoke(parkingLot, null));
			
			resultList.add(new BeanMap(parkingLot));
		}
		
		System.out.println(resultList);
	}
}
