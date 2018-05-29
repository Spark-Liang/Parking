package com.park.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.ssm.dao.AccountDao;
import com.park.ssm.dao.UserDao;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.User;
import com.park.ssm.entity.type.AccountState;
import com.park.ssm.service.ParkTerminalService;
import com.park.ssm.util.PersistentUtil;

@Service
public class ParkTerminalServiceImpl implements ParkTerminalService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private AccountDao accountDao;

	private Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * @throws RuntimeException("数据库异常：Account中存在异常用户")
	 */
	@Override
	public String park(Integer parkingLotId, Long cardId) {
		// TODO Auto-generated method stub
		// 检查该卡是否能够正常停车
		Account account = accountDao.findAccountrById(null, null, null, cardId, null, null).get(0);
		String reason = canPark(parkingLotId, account);
		if (reason != null) {
			return reason;
		}
		// 该停车卡能够正常使用，修改停车卡状态
		Account accountForUpdate = accountDao.loadAccountByIdForUpdate(account.getId());
		accountForUpdate.setParking(true);
		Map<String, Object> different=null;
		try {
			different = PersistentUtil.different(account,accountForUpdate);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(different!=null) {
			accountDao.modifyAccount(accountForUpdate.getId(),different);
		}
		return null;
	}

	/**
	 * 
	 * @param parkingLotId
	 * @param account
	 * @return reason(String)： null代表停车成功，not null 代表失败并返回提示信息
	 * @throws
	 *             <li>RuntimeException("数据库异常：Account中存在异常用户")
	 *             <li>RuntimeException("请输入正确的停车卡，该停车卡没有对应的账号")
	 */
	private String canPark(Integer parkingLotId, Account account) {
		// 判断是否能够找到停车账号
		if (account == null) {
			throw new RuntimeException("请输入正确的停车卡，该停车卡没有对应的账号");
		}

		// 判断是否与停车场对应
		if (!parkingLotId.equals(account.getParkingLotId())) {
			return "停车卡与停车场不匹配";
		}
		// 判断是否已经停车
		if (account.isParking()) {
			return "该停车卡已经停车，不能再次停车";
		}
		// 判断该停车卡是否是处于正常使用状态
		String reason = null;
		// 判断本身是否可以使用
		reason = accountCanUsed(account);
		if (reason != null) {
			return reason;
		}
		// 判断该停车卡对应的用户在此停车场下的其他停车卡是否全部可以用
		User user = userDao.loadUserById(account.getUserId());
		if (user == null) {
			RuntimeException exception = new RuntimeException("数据库异常：Account中存在异常用户");
			logger.error(exception);
			throw exception;
		}
		List<Account> accounts = accountDao.findAccountrById(account.getUserId(), parkingLotId, null, null, null, null);
		boolean flag = true;
		for (Account tmpAccount : accounts) {
			String tmpReason = accountCanUsed(tmpAccount);
			if (tmpReason != null) {
				reason = "该停车卡对应用户在该停车场存在其他不能使用的卡，该卡不能使用的原因是：" + tmpReason;
				flag = false;
				break;
			}
		}
		if (flag == false) {
			return reason;
		}

		// 该卡是能够正常停车
		return null;
	}

	// 判断给定的账户是否可用
	private String accountCanUsed(Account account) {
		if (!account.getState().equals(AccountState.NORMAL)) {
			return "该停车卡存在超期未支付的账单";
		}
		return null;
	}

	@Override
	public String pick(Integer parkingLotId, Long cardId) {
		// TODO Auto-generated method stub
		// 检查该卡是否能够正常提车
		Account account = accountDao.findAccountrById(null, null, null, cardId, null, null).get(0);
		String reason = canPick(parkingLotId, account);
		if (reason != null) {
			return reason;
		}
		// 该停车卡能够正常提车，修改停车卡状态
		Account accountForUpdate = accountDao.loadAccountByIdForUpdate(account.getId());
		accountForUpdate.setParking(false);
		Map<String, Object> different=null;
		try {
			different = PersistentUtil.different(account,accountForUpdate);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(different!=null) {
			accountDao.modifyAccount(accountForUpdate.getId(),different);
		}
		return null;
	}

	private String canPick(Integer parkingLotId, Account account) {
		// 判断是否与停车场对应
		if (!parkingLotId.equals(account.getParkingLotId())) {
			return "停车卡与停车场不匹配";
		}
		// 判断是否已经停车
		if (!account.isParking()) {
			return "该停车卡未停车，不能进行提车";
		}

		// 该卡是能够正常提车
		return null;
	}
}
