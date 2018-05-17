package com.park;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import com.park.ssm.dao.ParkingLotDao;

public class DemoParameterTest extends ParameterizedSpringTest{
	@Autowired
	private ParkingLotDao dao;
	
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][] {
			{"A",1},
			{"C",2},
		});
	}
	
	private String name;
	private Integer id;
	
	public DemoParameterTest(Object data1,Object data2) {
		super();
		this.name = (String)data1;
		this.id = (Integer)data2;
	}
	
	@Test
	public void test() {
		System.out.println("name="+name);
		System.out.println("parkingLot="+dao.loadParkingLotById(id));
	}

	
	
}
