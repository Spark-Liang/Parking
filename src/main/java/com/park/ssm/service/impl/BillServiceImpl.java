package com.park.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.BillDao;
import com.park.ssm.entity.Bill;
import com.park.ssm.entity.Account;
import com.park.ssm.service.BillService;

/**
 * billService实现类
 * @author ASNPHX4
 *
 */
@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;
	@Override
	public List<Bill> listBillById(Long userId,Long accountId,Integer lotId,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		//System.out.println("-----------------------"+billDao.listBillById(userId, accountId, lotId, pageNum, pageSize));
		return billDao.listBillById(userId, accountId, lotId, pageNum, pageSize);
	}
	
	@Override
	public int insertBill(Bill bill) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			result=billDao.addBill(bill);
		}catch(Exception e) {
			result=0;
		}
		return result;
	}

	@Override
	@Transactional
	public int payBill(Bill bill) {
		// TODO Auto-generated method stub
		StringBuilder errorMessage=new StringBuilder();
		int result=billDao.updateIsPaid(bill);
		if(result>0) {
			return result;
		}else {
			errorMessage.append("支付失败,请重试");
			throw new RuntimeException(errorMessage.toString());
		}
	}
}
