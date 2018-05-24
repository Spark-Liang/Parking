package com.park.ssm.service;

import java.util.List;

import com.park.ssm.entity.Account;
import com.park.ssm.entity.User;

public interface AccountService {
	
	public List<Account> findAccountrById(long userId);//根据用户ID获取用户所有的帐户信息
	
	public User findUserByuserId(long userId);//根据用户ID获取用户基础信息
	
	public int addNewCard(Account account);
}
