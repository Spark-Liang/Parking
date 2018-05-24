package com.park.ssm.entity;

public class User {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 客户帐号
	 */
	private Long userId;
	/**
	 * 密码
	 */
	private String password;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
