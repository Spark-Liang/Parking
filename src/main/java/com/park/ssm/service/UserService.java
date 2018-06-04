package com.park.ssm.service;

import com.park.ssm.entity.User;

public interface UserService {
	public User findUser(Long i,String password);//登陆验证
	
	public User findUserById(Long userId);//根据Id查找User
	
	public String findSaltByUserId(Long userId);//查找salt
	
	public Boolean insertUser(long userId,String password,String salt);//新增用户
	
}
