package com.park.ssm.service;

import com.park.ssm.entity.InnerUser;

public interface InnerUserService {
	public InnerUser findInnerUserByNickname(String nickname);//根据昵称查找用户,主要用于添加用户时验证昵称是否已经存在

	public int insertInnerUser(InnerUser innerUser);//增加用户，admin才具有此权限

	public InnerUser findInnerUser(String nickname, String password,int typeflag);//根据用户名和密码查找，主要用于用户登陆验证

	public int dropInnerUserByNickname(String nickname);//删除用户，admin才具有此权限

	public int changeInnerUserByNickname(InnerUser innerUser);//修改用户，admin才具有此权限
}
