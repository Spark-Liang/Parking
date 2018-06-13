package com.park.ssm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.ssm.dao.AccountDao;
import com.park.ssm.dao.AccountStateLogDao;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.AccountStateLog;
import com.park.ssm.entity.type.AccountState;
import com.park.ssm.service.AccountStateLogService;

@Service
public class AccountStateLogServiceImpl implements AccountStateLogService {
	@Autowired
	private AccountStateLogDao accountStateLogDao;
	@Autowired
	private AccountDao accountDao;
	
	
	/**
	 * @throws 
	 * <li>RuntimeException("输入错误：请输入对应的账号")
	 * <li>RuntimeException("系统内部错误：无法找到对应记录")
	 */
	@Override
	public Map<String, Object> getBillPropBeforeTerminated(Long accountId) {
		// TODO Auto-generated method stub
		Account account=accountDao.loadAccountById(accountId);
		if(account == null) {
			throw new RuntimeException("输入错误：请输入对应的账号");
		}
		List<AccountStateLog> accountStateLogs=
				accountStateLogDao.listLogByAccountId(accountId
													,AccountState.NORMAL
													,null, null, false, null, null);
		if(accountStateLogs == null || accountStateLogs.isEmpty()) {
			throw new RuntimeException("系统内部错误：无法找到对应记录");
		}
		Map<String, Object>billProperties=new HashMap<>();
		
		
		billProperties.put("price", account.getPrice());
		billProperties.put("stateLogs", accountStateLogs);
		return billProperties;
	}

}
