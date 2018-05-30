package com.park.ssm.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
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
	public abstract List<ParkingPosition> loadParkingPositionByLotId(int ParkingLotId);

	
	/**加载的ParkingPosition中所有的bean属性为代理对象*/
	public abstract ParkingPosition loadParkingPositionById(long id);
	/**加载的ParkingPosition中所有的bean属性为代理对象，且对数据表加锁*/
	public abstract ParkingPosition loadParkingPositionByIdForUpdate(long id);

	
	/**返回符合条件的所有parkingposition
	 * @param conditions 
	 * 	输入数字类型的条件Map,对应的condition名字有CONDITION 类对应
	 * <li>“id” 筛选parkingposition的id
	 * <li>“parkingLotId” 筛选对应的parkingLot的id
	 * <li>“accountId”  筛选对应的account的id
	 * <li>“states” 表示所有存在的state的list
	 * @param pageNum 页数
	 * @param pageSize 每页行数
	 * */
	public abstract List<ParkingPosition> listParkingPosition(@Param("map")Map<String, Object> conditions,@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
	
	/**添加停车位*/
	public int insertParkingPosition(List<Integer> list);
	
	/**
	 * 
	 * @param parkingPosition
	 * @return
	 */
	public int updateParkingPosition(@Param("id")Long id,@Param("parkingposition") ParkingPosition parkingPosition);
	
	
	/**删除停车位，即设置停车位状态为INACTIVE*/
	public abstract int deleteParkingPositionByLotId(int id);
	
	/**
	 * 
	 * @param parkingPosition
	 * 获取现在该停车场已被占用的停车位数量
	 * @param parkingLotId 停车场id 
	 * @return
	 */
	public abstract int getPositionNum(@Param("LotId")int LotId);
	
	/**
	 * 
	 * @param parkingPosition
	 * 获取该用户已经在该停车场占用的停车位数量
	 * @param parkingLotId 停车场id 
	 * @return
	 */
	public abstract int getPositionNumByUser(@Param("accountId")long accountId,@Param("parkingLotId")int parkingLotId);
	
}
