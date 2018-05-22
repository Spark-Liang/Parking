package com.park.ssm.dao;

import java.util.List;

import com.park.ssm.entity.Bill;

/**
 * 包含功能：
 * <p>查询方面功能
 * <li>通过id加载账单
 * <li>停车场，用户id,账户id查询账单
 * <p>更新功能：
 * <li>更新账单支付状态
 * 
 * @author LZH
 *
 */
public interface BillDao {
	
	public abstract Bill loadBillByIdForUpdate(long id);
	
	public abstract List<Bill> listBillByUserId(long id);
	public abstract List<Bill> listBillByAccountId(long id);
	
	public abstract List<Bill> listBillByParkingLotId(int id);
	
	/**
	 * 更新对应的id的bill的ispaid状态为1
	 * @param id
	 * @return
	 */
	public abstract int updateBillStateById(long id);
}
