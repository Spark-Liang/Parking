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
	private Long userId;
	private Integer parkingLotId;
	private Long accountId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Integer parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
