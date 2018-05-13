package com.park.ssm.dao;

import java.util.*;

import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.ParkingPosition;

/**
 * 
 * @author ASNPHXJ
 * 主要包含的操作：<br>
 * 查询相关：<br>
 * 1.通过停车场id查询
 * 2.通过停车位id查询
 * 与更新相关：
 * 
 */
@MapperScan
public interface ParkingPositionDao {
	
	/**加载的ParkingPosition中所有的bean属性为代理对象*/
	public abstract Set<ParkingPosition> loadParkingPositionByLotId(int ParkingLotId);

	
	/**加载的ParkingPosition中所有的bean属性为代理对象*/
	public abstract ParkingPosition loadParkingPositionById(long id);
	/**加载的ParkingPosition中所有的bean属性为代理对象，且对数据表加锁*/
	public abstract ParkingPosition loadParkingPositionByIdForUpdate(long id);

	
	
	/**添加停车位*/
	public int insertParkingPosition(List<Integer> list);
	
	/**删除停车位，即设置停车位状态为INACTIVE*/
	public abstract int deleteParkingPositionByLotId(int id);
	
	
	
}
