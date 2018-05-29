package com.park.ssm.entity;

import java.io.Serializable;

/**
 * 外部用户即停车场持卡停车用户实体类
 * @author ASNPHX4
 *
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	/**
	 * 盐
	 */
	private String salt;

	public User() {

	}

	public User(Long userId, String password) {
		this.userId = userId;
		this.password = password;
	}

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", salt=" + salt + "]";
	}

}
