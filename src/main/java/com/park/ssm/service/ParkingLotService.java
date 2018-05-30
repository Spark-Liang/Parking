package com.park.ssm.service;

import java.util.List;
import java.util.Map;

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
	 * @throws RuntimeException(错误信息)
	 * <li>数据库内部添加停车场事务异常，停车位更新失败
	 * */
	public abstract void saveParkingLot(ParkingLot parkingLot);
	
	/**
	 * 查询该停车场名称是否存在
	 * @param name
	 * @return boolean
	 */
	public abstract boolean isExistingName(String name);
	
	/**返回的parkingLot中的所有对象的所有的bean属性都不进行加载，只是生成代理类
	 * 默认返回未被删除的停车场信息
	 * @param conditions 
	 * 	输入数字类型的条件Map
	 * “name”,“location”
	 * "totalPositionNum_max","totalPositionNum_min" 表示totalPositionNum的边界
	 * “price_max”“price_min”表示currentPrice的边界
	 * “cost_max”“cost_min”表示cost的边界
	 * @param pageNum 页数 default 1
	 * @param pageSize 每页行数 default 20
	 * @param withPosition 表示返回的停车场是否包含停车位信息,当值为true则是包含
	 * */
	public abstract List<ParkingLot> listParkingLot(Map<String, Object> conditions,Integer pageNum,Integer pageSize,boolean withPosition);
	
	/**
	 * 查看符合条件的parkinglot总数
	 * @param conditions {@link com.park.ssm.service.ParkingLotService#listParkingLot}
	 * @return
	 */
	public abstract int countParkingLot(Map<String, Object> conditions);
	
	/**
	 * 更新停车场信息，之可以更新cost，price，location这几个参数
	 * @param parkingLot 更新的停车场
	 * @throws RuntimeException
	 * <li>数据库内部更新失败
	 * */
	public abstract void updateParkingLot(ParkingLot parkingLot);
	
	/**
	 * 把ParkingLot在数据库中标记为INACTIVE
	 * @return 
	 * true 修改成功  false 修改失败
	 * @throws RuntimeException
	 * <li>删除一个不存在的停车场
	 * <li>停车场中还存在正在使用的停车卡，不能进行删除
	 * <li>内部数据库错误，删除事务不完整，删除失败
	 * */
	public abstract void deleteParkingLot(Integer lotId) ;
	
	/**
	 * 把List中的ParkingLot在数据库中标记为INACTIVE
	 * @return 
	 * null 所有修改成功 ，如果返回List则List中的id为修改不成功  
	 * */
	public abstract List<Integer> listDeleteParkingLot(List<Integer> ids);
	
}
