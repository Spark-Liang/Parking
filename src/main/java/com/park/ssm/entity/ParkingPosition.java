package com.park.ssm.entity;

import com.park.ssm.entity.type.ParkingPositionState;

public class ParkingPosition {
	private Long id;
	private ParkingLot parkingLot;
	private Account account;
	private ParkingPositionState state;
	
	public ParkingPosition() {}
	
	
	public Long getId() {
		return id;
	}


	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ParkingPositionState getState() {
		return state;
	}
	public void setState(ParkingPositionState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ParkingPosition [id=" + id + ", parkingLot=" + parkingLot + ", account=" + account + ", state=" + state
				+ "]";
	}
	
	
}
