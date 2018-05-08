package com.park.ssm.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.Account;

@MapperScan
public interface AccountDao {

	public abstract Account loadAccountById(long id);
	
}
