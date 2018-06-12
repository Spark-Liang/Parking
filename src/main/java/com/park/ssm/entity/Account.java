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
@JsonIgnoreProperties(value= {"handler","serialVersionUID"},ignoreUnknown = true)
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2549308258379790138L;

	private Long id;
	private Long userId;
	private ParkingLot parkingLot;
	private Long parkingPositionId;
	private Long cardId;
	
	private AccountState state=AccountState.NORMAL;
	private Date stateStartDate;
	private boolean isParking;
	private Bill currentBill;
	private List<Bill> bills;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the parkingLot
	 */
	public ParkingLot getParkingLot() {
		return parkingLot;
	}
	/**
	 * @param parkingLot the parkingLot to set
	 */
	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	/**
	 * @return the parkingPositionId
	 */
	public Long getParkingPositionId() {
		return parkingPositionId;
	}
	/**
	 * @param parkingPositionId the parkingPositionId to set
	 */
	public void setParkingPositionId(Long parkingPositionId) {
		this.parkingPositionId = parkingPositionId;
	}
	/**
	 * @return the cardId
	 */
	public Long getCardId() {
		return cardId;
	}
	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	/**
	 * @return the state
	 */
	public AccountState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(AccountState state) {
		this.state = state;
	}
	/**
	 * @return the stateStartDate
	 */
	public Date getStateStartDate() {
		return stateStartDate;
	}
	/**
	 * @param stateStartDate the stateStartDate to set
	 */
	public void setStateStartDate(Date stateStartDate) {
		this.stateStartDate = stateStartDate;
	}
	/**
	 * @return the isParking
	 */
	public boolean isParking() {
		return isParking;
	}
	/**
	 * @param isParking the isParking to set
	 */
	public void setParking(boolean isParking) {
		this.isParking = isParking;
	}
	/**
	 * @return the currentBill
	 */
	public Bill getCurrentBill() {
		return currentBill;
	}
	/**
	 * @param currentBill the currentBill to set
	 */
	public void setCurrentBill(Bill currentBill) {
		this.currentBill = currentBill;
	}
	/**
	 * @return the bills
	 */
	public List<Bill> getBills() {
		return bills;
	}
	/**
	 * @param bills the bills to set
	 */
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", parkingLot=" + parkingLot + ", parkingPositionId="
				+ parkingPositionId + ", cardId=" + cardId + ", state=" + state + ", stateStartDate=" + stateStartDate
				+ ", isParking=" + isParking + ", currentBill=" + currentBill + ", bills=" + bills + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bills == null) ? 0 : bills.hashCode());
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		result = prime * result + ((currentBill == null) ? 0 : currentBill.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isParking ? 1231 : 1237);
		result = prime * result + ((parkingLot == null) ? 0 : parkingLot.hashCode());
		result = prime * result + ((parkingPositionId == null) ? 0 : parkingPositionId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((stateStartDate == null) ? 0 : stateStartDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
		if (bills == null) {
			if (other.bills != null)
				return false;
		} else if (!bills.equals(other.bills))
			return false;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		if (currentBill == null) {
			if (other.currentBill != null)
				return false;
		} else if (!currentBill.equals(other.currentBill))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isParking != other.isParking)
			return false;
		if (parkingLot == null) {
			if (other.parkingLot != null)
				return false;
		} else if (!parkingLot.equals(other.parkingLot))
			return false;
		if (parkingPositionId == null) {
			if (other.parkingPositionId != null)
				return false;
		} else if (!parkingPositionId.equals(other.parkingPositionId))
			return false;
		if (state != other.state)
			return false;
		if (stateStartDate == null) {
			if (other.stateStartDate != null)
				return false;
		} else if (!stateStartDate.equals(other.stateStartDate))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
