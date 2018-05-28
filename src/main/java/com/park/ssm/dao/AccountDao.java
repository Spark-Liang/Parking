package com.park.ssm.dao;

import java.util.List;

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
	
	public abstract int modifyAccount(Account account);
	
	public abstract int isNotExistCard(long cardId);
}
