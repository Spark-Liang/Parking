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
	
	/**
	 * 该账号对应新账单的每个月的单价
	 */
	private Double price;
	private AccountState state=AccountState.NORMAL;
	private Date stateStartDate;
	private boolean isParking;
	private Bill currentBill;
	private List<Bill> bills;
	
	public Long getId() {
		return id;
	}
	public Long getUserId() {
		return userId;
	}
	public ParkingLot getParkingLot() {
		return parkingLot;
	}
	public Long getParkingPositionId() {
		return parkingPositionId;
	}
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public Double getPrice() {
		return price;
	}
	public AccountState getState() {
		return state;
	}
	public Date getStateStartDate() {
		return stateStartDate;
	}
	public boolean isParking() {
		return isParking;
	}
	public Bill getCurrentBill() {
		return currentBill;
	}
	public List<Bill> getBills() {
		return bills;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		result = prime * result + ((currentBill == null) ? 0 : currentBill.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isParking ? 1231 : 1237);
		result = prime * result + ((parkingLot == null) ? 0 : parkingLot.hashCode());
		result = prime * result + ((parkingPositionId == null) ? 0 : parkingPositionId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((stateStartDate == null) ? 0 : stateStartDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
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
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", parkingLot=" + parkingLot + ", parkingPositionId="
				+ parkingPositionId + ", cardId=" + cardId + ", price=" + price + ", state=" + state
				+ ", stateStartDate=" + stateStartDate + ", isParking=" + isParking + ", currentBill=" + currentBill
				+ ", bills=" + bills + "]";
	}
	
	
	
}
