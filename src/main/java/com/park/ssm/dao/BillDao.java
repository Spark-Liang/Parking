package com.park.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.Bill;

/**
 * 包含功能：
 * <p>查询方面功能
 * <li>通过id加载账单
 * <li>停车场，用户id,账户id查询账单
 * <p>更新功能：
 * <li>更新账单支付状态
 * 
 * @author LZH
 *
 */
@MapperScan
public interface BillDao {
	
	public abstract Bill loadBillById(long id);
	public abstract Bill loadBillByIdForUpdate(long id);
	
	/**
	 * 通过不同id组合查找所有符合条件的账单
	 * @param userId
	 * @param accountId
	 * @param parkingLotId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public abstract List<Bill> listBillById(@Param("userId")Long userId
											,@Param("accountId")Long accountId
											,@Param("parkingLotId")Integer parkingLotId
											,@Param("pageNum")Integer pageNum
											,@Param("pageSize")Integer pageSize);
	/**
	 * 通过不同id组合查找所有符合条件的账单的总行数
	 * @param userId
	 * @param accountId
	 * @param parkingLotId
	 * @return
	 */
	public abstract int countBillById(@Param("userId")Long userId
											,@Param("accountId")Long accountId
											,@Param("parkingLotId")Integer parkingLotId);
	
	/**
	 * 更新对应的id的bill的ispaid状态为1
	 * @param id
	 * @return
	 */
	public abstract int updateBillStateById(long id);
	
	/**
	 * 查询是否存在bill的ispaid状态为1
	 * @param id
	 * @return
	 */
	public abstract int  isNotPayBill(@Param("userId")Long userId);
	
	public List<Bill> listBillByCardId(Long userId);//根据用户显示其所有账单
}
