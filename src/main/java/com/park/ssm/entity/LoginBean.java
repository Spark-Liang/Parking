package com.park.ssm.entity;

public class LoginBean {
	private String nickName;
	private int typeflag;
	
	public LoginBean() {}
	public LoginBean(String nickName, int typeflag) {
		super();
		this.nickName = nickName;
		this.typeflag = typeflag;
	}


	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getTypeflag() {
		return typeflag;
	}
	public void setTypeflag(int typeflag) {
		this.typeflag = typeflag;
	}
	
	
}
