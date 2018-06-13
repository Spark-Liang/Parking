package com.park.ssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.AccountStateLog;
import com.park.ssm.entity.type.AccountState;

@MapperScan
public interface AccountStateLogDao {
	
	/**通过accountId查找Log
	 * 
	 * @param accountId 账号id
	 * @param state 筛选的log中记录的AccountState的类型
	 * @param startTime 筛选log的开始时间
	 * @param endTime 筛选log的结束时间
	 * @param isLogWithBill 筛选log是否有bill对应，true筛选有bill对应，false筛选没有bill对应，null表示不限制
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public abstract List<AccountStateLog> listLogByAccountId(@Param("accountId")Long accountId
															,@Param("state")AccountState state
															,@Param("startTime")Date startTime
															,@Param("endTime")Date endTime
															,@Param("isLogWithBill")Boolean isLogWithBill
															,@Param("pageNum")Integer pageNum,@Param("pageSize")Integer pageSize);
	
	public Date selectStartDate(@Param("accountId")Long accountId);
}
