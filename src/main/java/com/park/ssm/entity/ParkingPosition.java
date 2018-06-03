package com.park.ssm.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.park.ssm.annotation.Permission;
import com.park.ssm.annotation.Permission.Type;
import com.park.ssm.entity.type.ParkingPositionState;

@JsonIgnoreProperties(value= {"handler","serialVersionUID"},ignoreUnknown = true)
@Permission(value= {Type.ADMIN,Type.MANAGER,Type.OPERATOR})
public class ParkingPosition implements Serializable {
	/**
	 */
	private static final long serialVersionUID = -7269728599839139227L;
	
	private Long id;
	private Long parkingLotId;
	private Long accountId;
	private ParkingPositionState state=ParkingPositionState.UNOCCUPIED;
	
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
