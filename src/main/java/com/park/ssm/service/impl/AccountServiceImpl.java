package com.park.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.park.ssm.dao.AccountDao;
import com.park.ssm.dao.BillDao;
import com.park.ssm.dao.ParkingLotDao;
import com.park.ssm.dao.ParkingPositionDao;
import com.park.ssm.dao.UserDao;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.ParkingPosition;
import com.park.ssm.entity.User;
import com.park.ssm.service.AccountService;
import com.park.ssm.util.PersistentUtil;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	private Logger logger=LogManager.getLogger(this.getClass());
	
	@Autowired
	private AccountDao accountdao;
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private BillDao billdao;
	
	@Autowired 
	private ParkingLotDao parkinglotdao;
	
	@Autowired
	private ParkingPositionDao parkingpositiondao;
	
	@Override
	public List<Account> findAccountrById(long userId) {
		
		return accountdao.findAccountrById(userId,null,null,null,null,null);
	}
	@Override
	public User findUserByuserId(long userId) {
		return userdao.loadUserById(userId);
	}
	
	@Override
	@Transactional
	public int addNewCard(Account account,int LotId) {
		int successAccount=accountdao.insertAccount(account);
		if(successAccount==0) {
		   	throw new RuntimeException("添加新的停车卡事务异常，新增停车卡失败");
		}
		else {
			ParkingPosition parkingPosition=new ParkingPosition();
			parkingPosition.setAccountId(account.getId());
			int successParkingPosition=parkingpositiondao.updateParkingPositionByLotId(LotId,parkingPosition);
			if(successParkingPosition==0) {
			   	throw new RuntimeException("更新停车位事务异常，更新停车位状态失败");
			}
			else {
				return 1;
			}
		}
	}
	@Override
	@Transactional
	public int modifyAccount(Account account) {
		
		Account accountInDB = accountdao.loadAccountByIdForUpdate(account.getId());
		Map<String, Object> different=null;
		try {
			different = PersistentUtil.different(accountInDB,account,Account.class);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			logger.info(e);
		}
		return accountdao.modifyAccount(account.getId(),different);
	}
	
	@Override
	@Transactional
	public int isNotExistCard(long cardId) {
		
		return accountdao.isNotExistCard(cardId);
	}

	@Override
	public Account getCardMessage(long cardId) {
		return accountdao.getCardMessage(cardId);
	}
	
	@Override
	@Transactional
	public int isNotPayBill(long userId) {
		return billdao.isNotPayBill(userId);
	}
	
	@Override
	@Transactional
	public Boolean isNotFullPosition(int id) {
		int PositionNum=parkingpositiondao.getPositionNum(id);//查看该停车场是否有空余车位
		if(PositionNum==0) {
			return true;
		}
		else{
			return false;	
		}
	}
//	@Override
//	public int getPositionNumByUser(long accountId, int LotId) {
//		return parkingpositiondao.getPositionNumByUser(accountId,LotId);
//	}

	@Override
	@Transactional
	public int getPositionNumByUser(long userId) {
		return accountdao.getAccountNum(userId);
	}
}
