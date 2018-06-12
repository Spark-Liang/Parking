package com.park.ssm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.Bill;

import junit.framework.Assert;

/**
 * 测试查看账单类
 * 
 * @author ASNPHX4
 *
 */
public class TestBillService extends AutoRollBackTest {

	@Autowired
	private BillService billService;

	@Autowired
	private AccountService accountService;

	@Test
	public void testListBillById() {
		List<Bill> list = new ArrayList<Bill>();
		list = billService.listBillByCardId(Long.valueOf(5689784l));
		Assert.assertEquals(3, list.get(0).getLastPayDate());
	}

	@Test
	public void testPayBill() {
		int result = 0;
		Bill bill = new Bill();
		bill.setPaid(true);
		bill.setId(Long.valueOf(6l));
		result = billService.payBill(bill);
		Assert.assertEquals(1, result);
	}
}
