package com.park.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

	public InnerUser getInnerUser(@Param("nickname")String nickname, @Param("password")String password);//根据用户名和密码查找，主要用于用户登陆验证

	public int deleteInnerUserByNickname(String nickname);//删除用户，admin才具有此权限

	public int updateInnerUserByNickname(InnerUser innerUser);//修改用户，admin才具有此权限
	
	public List<InnerUser> getInnerUserByTypeflag();//显示manager和operator
	
	public String getSaltByNickname(String nickname);//取出盐
	//public List<InnerUser> getInnerUserByFuzzy(@Param("nickname")String nickname,@Param("sex")int sex,@Param("phone")int phone);//根据nickname,sex,phone查询InnerUser
}
