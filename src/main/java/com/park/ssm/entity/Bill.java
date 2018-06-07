package com.park.ssm.entity;

import java.io.Serializable;
import java.util.Date;
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
	private User user;
	private ParkingLot parkingLot;
	private Account account;
	//计费相关
	private Double price;
	private Date billStartDate;
	private Date billEndDate;
	private boolean isPaid;
	private Date lastPayDate;
	
	private Long userId;
	private Long parkingLotId;
	private Long accountId;
	
	public Date getLastPayDate() {
		return lastPayDate;
	}

	public void setLastPayDate(Date lastPayDate) {
		this.lastPayDate = lastPayDate;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public Account getAccount() {
		return account;
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
//----------------------------------------------------
	
	@Override
	public String toString() {
		return "Bill [id=" + id + ", user=" + user + ", parkingLot=" + parkingLot + ", account=" + account + ", price="
				+ price + ", billStartDate=" + billStartDate + ", billEndDate=" + billEndDate + ", isPaid=" + isPaid
				+ ", lastPayDate=" + lastPayDate + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setBillStartDate(Date billStartDate) {
		this.billStartDate = billStartDate;
	}

	public void setBillEndDate(Date billEndDate) {
		this.billEndDate = billEndDate;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((billEndDate == null) ? 0 : billEndDate.hashCode());
		result = prime * result + ((billStartDate == null) ? 0 : billStartDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isPaid ? 1231 : 1237);
		result = prime * result + ((lastPayDate == null) ? 0 : lastPayDate.hashCode());
		result = prime * result + ((parkingLot == null) ? 0 : parkingLot.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
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
		if (lastPayDate == null) {
			if (other.lastPayDate != null)
				return false;
		} else if (!lastPayDate.equals(other.lastPayDate))
			return false;
		if (parkingLot == null) {
			if (other.parkingLot != null)
				return false;
		} else if (!parkingLot.equals(other.parkingLot))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
}
