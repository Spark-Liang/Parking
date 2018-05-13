package com.park.ssm.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.park.ssm.entity.ParkingLot;

/**
 * 包含的基本功能：
 * 1.对停车场的增加
 * 2.对停车场的删除和批量删除
 * 
 * @author ASNPHXJ
 *
 */
@Service 
public interface ParkingLotService {
	
	/**
	 * 保存ParkingLot对象到数据库
	 * @return 
	 * true 保存成功  false 保存失败
	 * */
	public abstract boolean saveParkingLot(ParkingLot parkingLot);
	
	/**
	 * 把ParkingLot在数据库中标记为INACTIVE
	 * @return 
	 * true 修改成功  false 修改失败
	 * */
	public abstract boolean deleteParkingLot(ParkingLot parkingLot);
	/**
	 * 把List中的ParkingLot在数据库中标记为INACTIVE
	 * @return 
	 * null 所有修改成功 ，如果返回List则List中的ParkingLot为修改不成功  
	 * */
	public abstract List<ParkingLot> listDeleteParkingLot(List<ParkingLot> parkingLots);
	
}
