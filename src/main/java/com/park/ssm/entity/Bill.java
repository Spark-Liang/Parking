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
	private Long userId;
	private Long parkingLotId;
	private Long accountId;
	//计费相关
	private Double price;
	private List<TimeQuantum> timeQuantums;
	private boolean isPaid;
	private Date lastPayDate;//用户最晚缴费日期
	
	public Long getId() {
		return id;
	}
	public Long getUserId() {
		return userId;
	}
	public Long getParkingLotId() {
		return parkingLotId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public Double getPrice() {
		return price;
	}
	public List<TimeQuantum> getTimeQuantums() {
		return timeQuantums;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public Date getLastPayDate() {
		return lastPayDate;
	}
	
	
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", userId=" + userId + ", parkingLotId=" + parkingLotId + ", accountId=" + accountId
				+ ", price=" + price + ", timeQuantums=" + timeQuantums + ", isPaid=" + isPaid + ", lastPayDate="
				+ lastPayDate + "]";
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isPaid ? 1231 : 1237);
		result = prime * result + ((lastPayDate == null) ? 0 : lastPayDate.hashCode());
		result = prime * result + ((parkingLotId == null) ? 0 : parkingLotId.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((timeQuantums == null) ? 0 : timeQuantums.hashCode());
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
		if (timeQuantums == null) {
			if (other.timeQuantums != null)
				return false;
		} else if (!timeQuantums.equals(other.timeQuantums))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
}
