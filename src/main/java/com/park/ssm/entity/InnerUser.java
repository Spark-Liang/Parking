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
	private int sex;				  //性别，1男2女
	private int phone;				  //手机号码，一般为11位
	private String address;			  //用户住址
	
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
	
	public InnerUser(String nickname,String password,int typeflag,String name,int sex,int phone,String address){
		this.nickname=nickname;
		this.password=password;
		this.typeflag=typeflag;
		this.name=name;
		this.sex=sex;
		this.phone=phone;
		this.address=address;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "InnerUser [nickname=" + nickname + ", password=" + password + ", typeflag=" + typeflag + ", name="
				+ name + ", sex=" + sex + ", phone=" + phone + ", address=" + address + "]";
	}
	
	
}
