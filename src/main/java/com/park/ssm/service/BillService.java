package com.park.ssm.service;

import java.util.List;

import com.park.ssm.entity.Account;
import com.park.ssm.entity.Bill;

/**
 * bill服务层
 * 
 * @author ASNPHX4
 *
 */
public interface BillService {
	/**
	 * 
	 * @param cardId
	 * @return
	 */
	public List<Bill> listBillById(Long userId,Long accountId,Integer lotId,Integer pageNum,Integer pageSize);
	
	/**
	 * 增加Bill
	 * @param bill
	 * @return
	 */
	public int insertBill(Bill bill);
	
	/**
	 * 支付账单
	 * @param bill
	 * @return
	 */
	public int normalPayBill(Bill bill);
	
	/**在用户付账单之前为他占用相应的停车位
	 * 
	 * @param accountId
	 * @return Long parkingPositionId
	 */
	public abstract Long bookPositionBeforePayBill(Long accountId);
	
	/**当用户支付失败的时候取消他占用的停车位
	 * 
	 * @param parkingPositonId
	 */
	public abstract void unBookPosition(Long parkingPositonId);
	
	/**当用户完成支付的时候，给该用户实际注册停车位
	 * 
	 * @param accountId
	 * @param parkingPositionId
	 */
	public abstract void finishBillPaying(Long accountId,Long parkingPositionId);
}
