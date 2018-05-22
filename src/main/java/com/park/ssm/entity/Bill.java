package com.park.ssm.entity;

import java.util.Date;

/**
 * 账单类，包含系统中所有账单的信息，即包含历史账单和当前账单
 * @author LZH
 *
 */
public class Bill {
	private Long id;
	//信息相关
	private User user;
	private ParkingLot parkingLot;
	private Account account;
	//计费相关
	private Double price;
	private Date billStartDate;
	private Date billEndDate;
	private boolean isPaid;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ParkingLot getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getBillStartDate() {
		return billStartDate;
	}
	public void setBillStartDate(Date billStartDate) {
		this.billStartDate = billStartDate;
	}
	public Date getBillEndDate() {
		return billEndDate;
	}
	public void setBillEndDate(Date billEndDate) {
		this.billEndDate = billEndDate;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	
}
