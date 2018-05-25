package com.park.ssm.entity;

import com.park.ssm.entity.type.ParkingPositionState;

public class ParkingPosition {
	private Long id;
	private Long parkingLotId;
	private Long accountId;
	private ParkingPositionState state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParkingLotId() {
		return parkingLotId;
	}
	public void setParkingLotId(Long parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public ParkingPositionState getState() {
		return state;
	}
	public void setState(ParkingPositionState state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "ParkingPosition [id=" + id + ", parkingLotId=" + parkingLotId + ", accountId=" + accountId + ", state="
				+ state + "]";
	}
	
	
	
	
}
