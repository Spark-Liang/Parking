package com.park.ssm.dao;

import com.park.ssm.entity.InnerUser;

/**
 * dao层InnerUser接口
 * 
 * @author ASNPHX4
 *
 */

public interface InnerUserDao {
	public InnerUser getInnerUserByNickname(String nickname);//根据昵称查找用户,主要用于添加用户时验证昵称是否已经存在

	public int addInnerUser(InnerUser innerUser);//增加用户，admin才具有此权限

	public InnerUser getInnerUser(String nickname, String password);//根据用户名和密码查找，主要用于用户登陆验证

	public int deleteInnerUserByNickname(String nickname);//删除用户，admin才具有此权限

	public int updateInnerUserByNickname(InnerUser innerUser,String nickname);//修改用户，admin才具有此权限
}
