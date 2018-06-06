package com.park.ssm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.park.AutoRollBackTest;
import com.park.ssm.entity.Bill;

import junit.framework.Assert;
/**
 * 测试查看账单类
 * @author ASNPHX4
 *
 */
public class TestBillService extends AutoRollBackTest {

	@Autowired
	private BillService billService;

	@Test
	public void testFindBillByUserId() {
		List<Bill> list = new ArrayList<Bill>();
		list = billService.listBillByCardId(Long.valueOf(5689784l));
		Assert.assertEquals(3, list.get(0).getLastPayDate());
	}

}
