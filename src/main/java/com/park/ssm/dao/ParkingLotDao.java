package com.park.ssm.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.ParkingLot;
import com.park.ssm.entity.ParkingLotState;

/**
 * @author LZH
 *
 */
@MapperScan
public interface ParkingLotDao {
	
	
	/**加载所有的属性，包括加载parkingposition的bean信息，但是parkingposition中的bean不加载*/
	public abstract ParkingLot getParkingLotById(int id);
	
	/**所有的bean属性都不进行加载，只是生成代理类*/
	public abstract ParkingLot loadParkingLotById(int id);
	/**所有的bean属性都不进行加载，只是生成代理类,并且在存在事物的前提下加锁*/
	public abstract ParkingLot loadParkingLotByIdForUpdate(int id);
	
	/**返回的parkingLot中的所有对象的所有的bean属性都不进行加载，只是生成代理类*/
	public abstract List<ParkingLot> listParkingLot(@Param("exmaple")ParkingLot parkingLot
			,@Param("cost_gt")Double cost_gt,@Param("cost_lt")Double cost_lt
			,@Param("state") List<ParkingLotState> states
			,@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize );
	
	public abstract int insertParkingLot(ParkingLot parkingLot);
	public abstract int listInsertParkingLot(List<ParkingLot> parkingLots);
	
	public abstract int updateParkingLot(ParkingLot parkingLot);
	
	/**删除停车场方法，数据库操作为改变标志位为INACTIVE*/
	public abstract int deleteParkingLot(int id);
	public abstract int listDeleteParkingLot(List<Integer> ids);
}
