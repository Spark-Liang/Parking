package com.park.ssm.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.User;

@MapperScan
public interface UserDao {
	
	public abstract User loadUserById(long id);
}
