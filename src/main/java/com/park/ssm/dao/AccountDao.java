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

	/**通过AccountId获取Account的基础信息
	 * 
	 * @param id
	 * @return
	 */
	public abstract Account loadAccountById(long id);
	public abstract Account loadAccountByIdForUpdate(long id);
	
	/**执行添加新卡的过程，执行结果通过输入的map返回
	 * 
	 * result中
	 * <p>accountId 表示添加之后生成的account的Id
	 * <p>flag是执行结果的标志位 
	 * flag＝0表示执行成功
	 * flag 第一个bit位为1表示由于用户存在其他非正常状态的卡
	 * flag 第二个bit位为1表示由于没有停车位
	 * 
	 * @param map
	 * <li>parkingLotId 停车场id
	 * <li>userId 用户的id
	 * <li>cardId 停车卡Id
	 */
	public abstract void addNewCard(Map<String, Object> map);
	
	/**执行添加新卡的过程，执行结果通过输入的map返回
	 * 
	 * result中
	 * <p>flag是执行结果的标志位 
	 * flag＝0表示执行成功
	 * flag=1 表示执行失败
	 * 
	 * @param map
	 * <li>positionId 停车位id
	 * <li>accountId 账号的id
	 */
	public abstract void resumeCard(Map<String, Object> map);
	
	
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
	public abstract int countAccountrById(@Param("userId")Long userId
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
	
	/**提供accountid，执行停车操作 
	 * @param map
	 * <li>in accountId 需要停车的accountId
	 * <li>out flag 停车结果
	 */
	public void parkCar(Map<String, Object> map);
	
	/**提供accountid，执行提车操作 
	 * @param map
	 * <li>in accountId 需要停车的accountId
	 * <li>out flag 停车结果
	 */
	public void pickCar(Map<String, Object> map);
	
	/** 
	 * @param cardId
	 * 更新停车卡状态
	 */
	public abstract int updateCardStatus(@Param("cardId")long cardId,@Param("state")AccountState state);
	
	/**
	 * 当到每个季度的下一个月的第一天凌晨0点整 ，自动停止未支付帐单的停车卡与回收相应车位
	 * @param map
	 */
	public abstract void updateAllAccountState(Map<String,Object>map);
	
}
