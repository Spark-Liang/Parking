package com.park.ssm.entity;

import java.util.List;
import com.park.ssm.entity.type.AccountState;
/**
 * 账户类，每个账户都会对应一个账户类
 * @author LZH
 * 
 */
public class Account {
	private Long id;
	private User user;
	private ParkingLot parkingLot;
	private ParkingPosition parkingPosition;
	private Long cardId;
	
	private AccountState state;
	private Bill currentBill;
	private List<Bill> bills;
	
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
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public AccountState getState() {
		return state;
	}
	public void setState(AccountState state) {
		this.state = state;
	}
	public Bill getCurrentBill() {
		return currentBill;
	}
	public void setCurrentBill(Bill currentBill) {
		this.currentBill = currentBill;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	
	

}
