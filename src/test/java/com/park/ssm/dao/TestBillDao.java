package com.park.ssm.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.Bill;
import com.park.ssm.entity.TimeQuantum;

import junit.framework.Assert;

public class TestBillDao extends AutoRollBackTest{
	@Autowired
	private BillDao dao;
	
	@Test
	public void TestGetBill() {
		//test data
		Long id=1L;
		
		Bill bill=dao.loadBillById(id);
		Assert.assertNotNull(bill);
		
		id=3L;
		bill=dao.loadBillById(id);
		Assert.assertNotNull(bill);
		List<TimeQuantum> timeQuantums=bill.getTimeQuantums();
		Assert.assertEquals(2, timeQuantums);
	}
}
