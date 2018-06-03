package com.park.ssm.exception;

/**当用户为进行登录时，对系统内有进行权限控制的地方进行访问，都会抛LoginException。
 * 
 * @author LZH
 *
 */
public class LoginException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1402154953427373877L;
	
	private String loginUrl;
	
	public LoginException(String message,String loginUrl) {
		super(message);
		this.loginUrl=loginUrl;
	}

	public String getLoginUrl() {
		return loginUrl;
	}
	
	
}
