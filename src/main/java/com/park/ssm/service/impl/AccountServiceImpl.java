package com.park.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.ssm.dao.AccountDao;
import com.park.ssm.dao.UserDao;
import com.park.ssm.entity.Account;
import com.park.ssm.entity.User;
import com.park.ssm.service.AccountService;
import com.park.ssm.util.PersistentUtil;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	
	@Autowired
	private AccountDao accountdao;
	
	@Autowired
	private UserDao userdao;
	
	@Override
	public List<Account> findAccountrById(long userId) {
		// TODO Auto-generated method stub
		return accountdao.findAccountrById(userId,null,null,null,null,null);
	}
	@Override
	public User findUserByuserId(long userId) {
		return userdao.loadUserById(userId);
	}
	@Override
	public int addNewCard(Account account) {
		// TODO Auto-generated method stub
		return accountdao.insertAccount(account);
	}
	@Override
	public int modifyAccount(Account account) {
		// TODO Auto-generated method stub
		Account accountForUpdate = accountdao.loadAccountByIdForUpdate(account.getId());
		Map<String, Object> different=null;
		try {
			different = PersistentUtil.different(accountForUpdate, account);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountdao.modifyAccount(different);
	}
	
	@Override
	public int isNotExistCard(long cardId) {
		// TODO Auto-generated method stub
		return accountdao.isNotExistCard(cardId);
	}

	@Override
	public Account getCardMessage(long cardId) {
		// TODO Auto-generated method stub
		return accountdao.getCardMessage(cardId);
	}


}
