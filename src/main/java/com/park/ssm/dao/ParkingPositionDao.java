package com.park.ssm.dao;

import java.util.*;

import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.ParkingPosition;

@MapperScan
public interface ParkingPositionDao {
	
	/**加载的ParkingPosition中所有的bean属性为代理对象*/
	public abstract List<ParkingPosition> loadParkPositionByLotId(int ParkingLotId);
	/**加载的ParkingPosition中所有的bean属性为实际代理对象，但是bean属性内的bean属性为代理对象*/
	public abstract List<ParkingPosition> listParkPositionByLotId(int ParkingLotId);
	
	/**加载的ParkingPosition中所有的bean属性为代理对象*/
	public abstract ParkingPosition loadParkingPositionById(long id);
	/**加载的ParkingPosition中所有的bean属性为代理对象，且对数据表加锁*/
	public abstract ParkingPosition loadParkingPositionByIdForUpdate(long id);
	/**加载的ParkingPosition中所有的bean属性为实际代理对象，但是bean属性内的bean属性为代理对象*/
	public abstract ParkingPosition getParkingPositionById(long id);
	
	
	//public abstract int updateParkingPosition(ParkingPosition parkingPosition);
	
	
}
