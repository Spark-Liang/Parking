package com.park.ssm.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.park.ssm.entity.Bill;

@Service
public interface AccountStateLogService {
	
	/**通过accountId查找该用户停卡的时候需要另外支付的账单的属性的信息
	 * 
	 * @param accountId
	 * @return
	 */
	public abstract Map<String, Object> getBillBeforeTerminated(Long accountId);
}
