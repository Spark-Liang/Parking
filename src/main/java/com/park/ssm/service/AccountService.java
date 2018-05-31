package com.park.ssm.service;

import java.util.List;

import com.park.ssm.entity.Account;
import com.park.ssm.entity.User;

public interface AccountService {
	
	public List<Account> findAccountrById(long userId,boolean isFindAll);//根据用户ID获取用户所有的帐户信息
	
	public User findUserByuserId(long userId);//根据用户ID获取用户基础信息
	
	/**
	 * 添加新的卡，并且返回相应的账号id
	 * @param cardId
	 * @param userId
	 * @param LotId
	 * @return Long acccountId
	 */
	public Long addNewCard(Long cardId,Long userId,Integer lotId);
	
	public int modifyAccount(Account account);//修改客户的帐户信息
	
	public int isNotExistCard(long cardId);//是否已存在该停车卡
	
	public Account getCardMessage(long cardId);//根据停车卡号获取帐户信息

	public int isNotPayBill(long userId);//根据用户ID查看该用户是否存在未缴费的账单
	
	public Boolean isNotFullPosition(int LotId);//根据停车场编号判断是否有空余车位
	
//	public int getPositionNumByUser(long accountId,int LotId);//
	
	public int getPositionNumByUser(long userId);//获取用户在该停车场的停车卡数量
	
}
