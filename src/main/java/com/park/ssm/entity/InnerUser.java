package com.park.ssm.entity;

import java.io.Serializable;
/**
 * 内部用户实体类，适用于admin,manager,operator
 * @author ASNPHX4
 *
 */
public class InnerUser implements Serializable {
	private String nickname;          //昵称即用户名，登陆时使用昵称，不允许重复
	private String password;		  //密码，不允许低于8位
	private int typeflag;			  //类型标记，1为admin，2为manager，3为operator
	private String name;			  //用户真实姓名，允许同姓名
	
	
	public InnerUser() {
		
	}
	
	public InnerUser(String nickname,String password) {
		this.nickname=nickname;
		this.password=password;
	}
	
	public InnerUser(String nickname,String password,int typeflag) {
		this.nickname=nickname;
		this.password=password;
		this.typeflag=typeflag;
	}
	
	public InnerUser(String nickname,String password,int typeflag,String name){
		this.nickname=nickname;
		this.password=password;
		this.typeflag=typeflag;
		this.name=name;
		
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTypeflag() {
		return typeflag;
	}

	public void setTypeflag(int typeflag) {
		this.typeflag = typeflag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "InnerUser [nickname=" + nickname + ", password=" + password + ", typeflag=" + typeflag + ", name="
				+ name + "]";
	}
	
	
}
