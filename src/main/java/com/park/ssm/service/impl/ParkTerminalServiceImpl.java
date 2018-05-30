package com.park.ssm.service.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.AccountDao;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.type.AccountState;
import com.park.ssm.service.ParkTerminalService;
import com.park.ssm.util.PersistentUtil;

@Service
public class ParkTerminalServiceImpl implements ParkTerminalService {
	//@Autowired
	//private UserDao userDao;
	@Autowired
	private AccountDao accountDao;

	private Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * @throws RuntimeException("数据库异常：Account中存在异常用户")
	 */
	@Override
	@Transactional
	public String park(Integer parkingLotId, Long cardId) {
		// TODO Auto-generated method stub
		// 检查该卡是否能够正常停车
		Account account = accountDao.findAccountrById(null, null, null, cardId, null, null).get(0);
		String reason = canPark(parkingLotId, account);
		if (reason != null) {
			return reason;
		}
		// 该停车卡能够正常使用，修改停车卡状态
		try {
			Account accountInDB = accountDao.loadAccountByIdForUpdate(account.getId());
			account = new Account();
			PersistentUtil.<Account>merge(account, accountInDB, Account.class);
			account.setParking(true);

			Map<String, Object> different = null;
			different = PersistentUtil.different(accountInDB, account, Account.class);
			if (different != null) {
				accountDao.modifyAccount(account.getId(), different);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			reason = "系统内部错误，原因：" + e.getMessage();
			logger.info(e);
		}
		return reason;
	}

	/**
	 * 
	 * @param parkingLotId
	 * @param account
	 * @return reason(String)： null代表停车成功，not null 代表失败并返回提示信息
	 * @throws
	 *             <li>RuntimeException("数据库异常：Account中存在异常用户")
	 *             <li>RuntimeException("停车卡与停车场不匹配")
	 *             <li>RuntimeException("该停车卡已经停车，不能再次停车")
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
			throw new RuntimeException("停车卡与停车场不匹配");
		}
		// 判断是否已经停车
		if (account.isParking()) {
			throw new RuntimeException("该停车卡已经停车，不能再次停车");
		}
		// 判断该停车卡是否是处于正常使用状态
		String reason = null;
		// 判断本身是否可以使用
		reason = accountCanUsed(account);
		if (reason != null) {
			throw new RuntimeException(reason);
		}

		// 该卡是能够正常停车
		return null;
	}

	/**判断当前停车卡是否可用：
	 * <li>判断是否处于正常状态
	 * @param account
	 * @return
	 */
	private String accountCanUsed(Account account) {
		if (!account.getState().equals(AccountState.NORMAL)) {
			return "该停车卡存在超期未支付的账单";
		}
		return null;
	}
	/**
	 * 判断当前停车卡的对应用户的其他卡是否可用
	 * @param parkingLotId
	 * @param account
	 * @return
	 */
	/*private String checkOtherAccount(Integer parkingLotId,Account account) {
		String reason=null;
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
				throw new RuntimeException(reason);
			}
		}
		if (flag == false) {
			return reason;
		}
		return null;

	}*/

	@Override
	@Transactional
	public String pick(Integer parkingLotId, Long cardId) {
		// TODO Auto-generated method stub
		// 检查该卡是否能够正常提车
		Account account = accountDao.findAccountrById(null, null, null, cardId, null, null).get(0);
		String reason = canPick(parkingLotId, account);
		if (reason != null) {
			return reason;
		}
		// 该停车卡能够正常提车，修改停车卡状态
		try {
			Account accountInDB = accountDao.loadAccountByIdForUpdate(account.getId());
			account = new Account();
			PersistentUtil.<Account>merge(account, accountInDB, Account.class);
			account.setParking(false);

			Map<String, Object> different = null;
			different = PersistentUtil.different(accountInDB, account, Account.class);
			if (different != null) {
				accountDao.modifyAccount(account.getId(), different);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			reason = "系统内部错误，原因：" + e.getMessage();
			logger.info(e);
		}
		return reason;
	}

	private String canPick(Integer parkingLotId, Account account) {
		// 判断是否能够找到停车账号
		if (account == null) {
			throw new RuntimeException("请输入正确的停车卡，该停车卡没有对应的账号");
		}
		// 判断是否与停车场对应
		if (!parkingLotId.equals(account.getParkingLotId())) {
			throw new RuntimeException("停车卡与停车场不匹配");
		}
		// 判断是否已经停车
		if (!account.isParking()) {
			throw new RuntimeException("该停车卡未停车，不能进行提车");
		}

		// 该卡是能够正常提车
		return null;
	}
}
