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
	public List<Bill> listBillByCardId(Long cardId);
	
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
	public int payBill(Bill bill);
}
