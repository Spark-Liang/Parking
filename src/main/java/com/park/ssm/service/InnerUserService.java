package com.park.ssm.service;


import java.util.List;

import com.park.ssm.entity.InnerUser;
/**
 * 内部员工操作 
 * @author ASNPHX4
 *
 */
public interface InnerUserService {
	public InnerUser findInnerUserByNickname(String nickname);//根据昵称查找用户,主要用于添加用户时验证昵称是否已经存在

	public int insertInnerUser(InnerUser innerUser);//增加用户，admin才具有此权限

	public InnerUser findInnerUser(String nickname, String password);//根据用户名和密码查找，主要用于用户登陆验证

	public int dropInnerUserByNickname(String nickname);//删除用户，admin才具有此权限

	public int changeInnerUserByNickname(InnerUser innerUser);//修改用户，admin才具有此权限
	
	public List<InnerUser> findInnerUserByTypeflag();//根据typeflag查找InnerUser
	
	public String findSaltByNickname(String nickname);//取出盐值
	//public List<InnerUser> findInnerUserByFuzzy(String nickname,int sex,int phone);//根据条件查询InnerUser
}
