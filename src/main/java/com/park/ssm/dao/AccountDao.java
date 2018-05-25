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
	
	public abstract List<Account> findAccountrById(long userId);
	
	public abstract int modifyAccount(Account account);
	
	public abstract int isNotExistCard(long cardId);
}
