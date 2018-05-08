package com.park.ssm.entity;


public class ParkingPosition {
	private Long id;
	private ParkingLot parkingLot;
	private Account account;
	private ParkingPositionState state;
	
	public ParkingPosition() {}
	
	public Long getId() {
		return id;
	}
	public ParkingLot getParkingLotId() {
		return parkingLot;
	}
	public void setParkingLotId(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccountId(Account account) {
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
