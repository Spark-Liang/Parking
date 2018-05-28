package com.park.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.ssm.dao.UserDao;
import com.park.ssm.entity.User;
import com.park.ssm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 登陆验证
	 */
	@Override
	public User findUser(Long userId, String password) {
		// TODO Auto-generated method stub
		return userDao.getUser(userId, password);
	}
	
	/**
	 * 根据userId查找user
	 */
	@Override
	public User findUserById(Long userId) {
		// TODO Auto-generated method stub
		return userDao.loadUserById(userId);
	}
	
	/**
	 * 查找salt
	 */
	@Override
	public String findSaltByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userDao.getSaltByUserId(userId);
	}

}
