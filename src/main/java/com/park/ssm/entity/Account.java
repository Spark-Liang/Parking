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
	private Integer parkingLotId;
	private Long parkingPositionId;
	private Long cardId;
	
	private AccountState state=AccountState.NORMAL;
	private Date stateStartDate;
	private boolean isParking;
	private Bill currentBill;
	private List<Bill> bills;
	
	private List<ParkingLot> parkingLot;
	
	public List<ParkingLot> getParkingLot() {
		return parkingLot;
	}
	public void setParkingLot(List<ParkingLot> parkingLot) {
		this.parkingLot = parkingLot;
	}
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
	public AccountState getState() {
		return state;
	}
	public void setState(AccountState state) {
		this.state = state;
	}
	public Date getStateStartDate() {
		return stateStartDate;
	}
	public void setStateStartDate(Date stateStartDate) {
		this.stateStartDate = stateStartDate;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", parkingLotId=" + parkingLotId + ", parkingPositionId="
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
		result = prime * result + ((parkingLotId == null) ? 0 : parkingLotId.hashCode());
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
		if (parkingLotId == null) {
			if (other.parkingLotId != null)
				return false;
		} else if (!parkingLotId.equals(other.parkingLotId))
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
