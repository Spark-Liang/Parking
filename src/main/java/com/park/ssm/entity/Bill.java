package com.park.ssm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 账单类，包含系统中所有账单的信息，即包含历史账单和当前账单
 * @author LZH
 *
 */
@JsonIgnoreProperties(value= {"handler","serialVersionUID"},ignoreUnknown = true)
public class Bill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3688859156173625270L;
	
	private Long id;
	//信息相关
	private Account userId;
	private ParkingLot parkingLotId;
	private Account accountId;
	//计费相关
	private Double price;
	private Date billStartDate;
	private Date billEndDate;
	private boolean isPaid;
	
	private List<Account> account;
	
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
	
	public Long getId() {
		return id;
	}
	public Account getUserId() {
		return userId;
	}
	public ParkingLot getParkingLotId() {
		return parkingLotId;
	}
	public Account getAccountId() {
		return accountId;
	}
	public Double getPrice() {
		return price;
	}
	public Date getBillStartDate() {
		return billStartDate;
	}
	public Date getBillEndDate() {
		return billEndDate;
	}
	public boolean isPaid() {
		return isPaid;
	}
	
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((billEndDate == null) ? 0 : billEndDate.hashCode());
		result = prime * result + ((billStartDate == null) ? 0 : billStartDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isPaid ? 1231 : 1237);
		result = prime * result + ((parkingLotId == null) ? 0 : parkingLotId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		if (!(obj instanceof Bill))
			return false;
		Bill other = (Bill) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (billEndDate == null) {
			if (other.billEndDate != null)
				return false;
		} else if (!billEndDate.equals(other.billEndDate))
			return false;
		if (billStartDate == null) {
			if (other.billStartDate != null)
				return false;
		} else if (!billStartDate.equals(other.billStartDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isPaid != other.isPaid)
			return false;
		if (parkingLotId == null) {
			if (other.parkingLotId != null)
				return false;
		} else if (!parkingLotId.equals(other.parkingLotId))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
