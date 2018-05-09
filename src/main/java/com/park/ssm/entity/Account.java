package com.park.ssm.entity;

import java.util.Date;

import com.park.ssm.entity.type.AccountState;

public class Account {
	private Long id;
	private User user;
	private ParkingLot parkingLot;
	private ParkingPosition parkingPosition;
	private AccountState state;
	private Date billStartDate;
	private Date billEndDate;
	
	
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
	public ParkingPosition getParkingPosition() {
		return parkingPosition;
	}
	public void setParkingPosition(ParkingPosition parkingPosition) {
		this.parkingPosition = parkingPosition;
	}
	public AccountState getState() {
		return state;
	}
	public void setState(AccountState state) {
		this.state = state;
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
	
	
	

}
