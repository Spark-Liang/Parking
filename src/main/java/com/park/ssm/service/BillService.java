package com.park.ssm.service;

import java.util.List;

import com.park.ssm.entity.Bill;

/**
 * bill服务层
 * @author ASNPHX4
 *
 */
public interface BillService {
public List<Bill> findBillByUserId(Long userId);
}
