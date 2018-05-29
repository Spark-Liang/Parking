package com.park.ssm.service;

import java.util.List;

import com.park.ssm.entity.Account;
import com.park.ssm.entity.Bill;
import com.park.ssm.entity.User;

public interface AccountService {
	
	public List<Account> findAccountrById(long userId);//根据用户ID获取用户所有的帐户信息
	
	public User findUserByuserId(long userId);//根据用户ID获取用户基础信息
	
	public int addNewCard(Account account);//为客户新建停车卡
	
	public int modifyAccount(Account account);//修改客户的帐户信息
	
	public int isNotExistCard(long cardId);//是否已存在该停车卡
	
	public Account getCardMessage(long cardId);//根据停车卡号获取帐户信息

	
	public Bill isNotPayBill(long userId);
}
