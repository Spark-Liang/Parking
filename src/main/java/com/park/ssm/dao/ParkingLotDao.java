package com.park.ssm.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.park.ssm.entity.ParkingLot;

public interface ParkingLotDao {
	
	
	public abstract ParkingLot getParkingLot(int id);
	
	public abstract ParkingLot loadParkingLot(int id);
	
	public abstract List<ParkingLot> listParkingLot(@Param("exmaple")ParkingLot parkingLot,@Param("cost_gt")Double cost_gt
			,@Param("cost_lt")Double cost_lt
			,@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize );
	
	public abstract int insertParkingLot(ParkingLot parkingLot);
	
	public abstract int updateParkingLot(ParkingLot parkingLot);

}
