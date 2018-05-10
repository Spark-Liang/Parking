package com.park.ssm.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import com.park.ssm.entity.ParkingLot;

/**
 *
 * @author LZH
 *  ParkingLotDao包含操作：<br>
 * 与查询相关：<br>
 * 1.通过id查询停车场
 * 2.通过条件筛选停车场信息
 * 与修改相关：<br>
 * 1.更新停车场信息
 *
 */

@MapperScan
public interface ParkingLotDao {


	
	/**所有的bean属性都不进行加载，只是生成代理类*/
	public abstract ParkingLot loadParkingLotById(int id);
	/**所有的bean属性都不进行加载，只是生成代理类,并且在存在事物的前提下加锁*/
	public abstract ParkingLot loadParkingLotByIdForUpdate(int id);

	
	/**返回的parkingLot中的所有对象的所有的bean属性都不进行加载，只是生成代理类
	 * @param conditions 
	 * 	输入数字类型的条件Map
	 * “name”,“location”
	 * "totalPositionNum_max","totalPositionNum_min" 表示totalPositionNum的边界
	 * “price_max”“price_min”表示currentPrice的边界
	 * “cost_max”“cost_min”表示cost的边界
	 * “states” 表示所有存在的state的list
	 * @param pageNum 页数
	 * @param pageSize 每页行数
	 * */
	public abstract List<ParkingLot> listParkingLot(@Param("map") Map<String, Object> conditions
			,@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize );
	
	
	public abstract int insertParkingLot(ParkingLot parkingLot);
	public abstract int listInsertParkingLot(List<ParkingLot> parkingLots);
	/**返回集合中所有状态为ACTIVE的停车场*/
	public abstract List<ParkingLot> listActiveLotByIdList(List<Integer> ids);
	
	
	public abstract int updateParkingLot(ParkingLot parkingLot);
	
	/**删除停车场方法，数据库操作为改变标志位为INACTIVE*/
	public abstract int deleteParkingLot(int id);
	public abstract int listDeleteParkingLot(List<Integer> ids);
}
