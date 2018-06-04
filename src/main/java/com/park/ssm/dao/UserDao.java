package com.park.ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.park.ssm.entity.User;

@MapperScan
public interface UserDao {
	
	public abstract User loadUserById(long userId);//根据Id查找User
	
	public User getUser(@Param("userId")Long userId,@Param("password")String password);//登陆验证
	
	public String getSaltByUserId(Long userId);//取出盐
	
	public int insertUser(@Param("userId")Long userId,@Param("password")String password,@Param("salt")String salt);//新增用户
}
