package com.park.ssm.entity;

public class LogonBean {
	private String nickName;
	private int typeflag;
	
	public LogonBean() {}
	public LogonBean(String nickName, int typeflag) {
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
