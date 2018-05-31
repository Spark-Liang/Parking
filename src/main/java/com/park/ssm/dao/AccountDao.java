package com.park.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.Account;
import com.park.ssm.entity.type.AccountState;

/**AccountDao功能：
 * <li>添加账户
 * <li>查找账户
 * <li>设置账户状态
 * 
 * @author ASNPHXJ
 *
 */
@MapperScan
public interface AccountDao {
	/**
	 * 通过account id查询account所有信息，连Bill也一起查询出来
	 * @param id
	 * @return
	 */
	public abstract Account loadAccountById(long id);
	public abstract Account loadAccountByIdForUpdate(long id);
	
	public abstract int insertAccount(Account account);
	
	public abstract int setAccountState(@Param("id") Long id,@Param("state")AccountState state); 
	
	/**
	 * 通过不同的id组合查找account
	 * @param userId
	 * @param parkingLotId
	 * @param parkingPositionId
	 * @param cardId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public abstract List<Account> findAccountrById(@Param("userId")Long userId
												,@Param("parkingLotId")Integer parkingLotId
												,@Param("parkingPositionId")Long parkingPositionId
												,@Param("cardId")Long cardId
												,@Param("pageNum")Integer pageNum
												,@Param("pageSize")Integer pageSize
												);
	/**
	 * 查看通过不同的id组合查找account的总行数
	 * @param parkingLotId
	 * @param parkingPositionId
	 * @param cardId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public abstract List<Account> countAccountrById(@Param("userId")Long userId
												,@Param("parkingLotId")Integer parkingLotId
												,@Param("parkingPositionId")Long parkingPositionId
												,@Param("cardId")Long cardId
												);
	/**
	 * 修改对应id的account
	 * @param id
	 * @param different 通过PersistentUtil方法得到的当前新的Account与数据库中的Account不同的属性集
	 * @return
	 */
	public abstract int modifyAccount(@Param("id")Long id,@Param("map")Map<String, Object> different);
	/**
	 * @param cardId
	 * 查询是否存在此卡
	 */
	public abstract int isNotExistCard(long cardId);
	/**
	 * @param cardId
	 * 获取停车卡信息
	 */
	public abstract Account getCardMessage(long cardId);
	/**
	 * @param userId
	 * 获取用户在该停车场的停车卡数量
	 */
	public abstract int getAccountNum(long userId);
	
}
