package com.park.ssm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.park.ssm.entity.type.AccountState;
/**
 * 账户类，每个账户都会对应一个账户类
 * @author LZH
 * 
 */
@JsonIgnoreProperties(value= {"handler"},ignoreUnknown = true)
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2549308258379790138L;

	private Long id;
	private Long userId;
	private Integer parkingLotId;
	private Long parkingPositionId;
	private Long cardId;
	
	private AccountState state;
	private Date stateStartDate;
	private boolean isParking;
	private Bill currentBill;
	private List<Bill> bills;
	
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
	public Long getParkingPositionId() {
		return parkingPositionId;
	}
	public void setParkingPositionId(Long parkingPositionId) {
		this.parkingPositionId = parkingPositionId;
	}
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public Date getStateStartDate() {
		return stateStartDate;
	}
	public void setStateStartDate(Date stateStartDate) {
		this.stateStartDate = stateStartDate;
	}
	public AccountState getState() {
		return state;
	}
	public void setState(AccountState state) {
		this.state = state;
	}
	public boolean isParking() {
		return isParking;
	}
	public void setParking(boolean isParking) {
		this.isParking = isParking;
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
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", parkingLotId=" + parkingLotId + ", parkingPositionId="
				+ parkingPositionId + ", cardId=" + cardId + ", stateStartDate=" + stateStartDate + ", state=" + state
				+ ", isParking=" + isParking + ", currentBill=" + currentBill + ", bills=" + bills + "]";
	}
	
	
	
}
