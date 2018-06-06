package com.park.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.ssm.dao.BillDao;
import com.park.ssm.entity.Bill;
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
	public List<Bill> listBillByCardId(Long userId) {
		// TODO Auto-generated method stub
		System.out.println("-----------------------"+billDao.listBillByCardId(userId));
		return billDao.listBillByCardId(userId);
	}

}
