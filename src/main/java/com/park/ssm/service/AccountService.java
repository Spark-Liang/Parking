package com.park.ssm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.park.ssm.entity.Account;
import com.park.ssm.entity.User;

public interface AccountService {
	
	/**
	 * 通过不同的id组合返回Account的列表，isFindAll标识标识是否返回Account的所有Bill信息
	 * @param userId
	 * @param parkingLotId
	 * @param parkingPositionId
	 * @param cardId
	 * @param pageNum
	 * @param pageSize
	 * @param isFindAll
	 * @return
	 */
	public List<Account> findAccountrById(Long userId
										,Integer parkingLotId
										,Long parkingPositionId
										,Long cardId
										,Integer pageNum
										,Integer pageSize
										,boolean isFindAll);
	
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
	
	
	/**
	 * 查看是否存在该停车卡
	 * @param cardId
	 */
	public int isNotExistCard(long cardId);
	
	/**
	 * 根据停车卡号获取帐户信息
	 * @param cardId
	 */
	public Account getCardMessage(long cardId);
	
	/**
	 * 根据用户ID查看该用户是否存在未缴费的账单
	 * @param userId
	 */
	public int isNotPayBill(long userId,long parkingLotId);

	/**
	 * 根据停车场编号判断是否有空余车位
	 * @param LotId
	 */
	public Boolean isNotFullPosition(int LotId);
	
//	public int getPositionNumByUser(long accountId,int LotId);//
	
	/**
	 * 查看该用户在该停车场的账户数量
	 * @param lotId
	 * @param userId
	 * @return
	 */
	public int getPositionNumByUser(Integer lotId,long userId);

	/**
	 * 停止停车卡（操作员） 
	 * @param cardId
	 * @return
	 */
	public int stopCard(long cardId);
}
