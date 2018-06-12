package com.park.ssm.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 停车记录
 * @author ASNPHX4
 *
 */
public class ParkingRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7324989122949146129L;
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 停车场id
	 */
	private Integer parkingLotId;
	
	/**
	 * 账户id
	 */
	
	private Long accountId;
	
	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;
	
	public ParkingRecord() {
		
	}
	
	public Long getId() {
		return id;
	}

	public Integer getParkingLotId() {
		return parkingLotId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	@Override
	public String toString() {
		return "ParkingRecord [id=" + id + ", parkingLotId=" + parkingLotId + ", accountId=" + accountId
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
