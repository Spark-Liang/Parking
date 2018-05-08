package com.park.ssm.entity;


public class ParkingPosition {
	private Long id;
	private ParkingLot parkingLot;
	private Account account;
	private boolean usedFlag;
	
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
	public boolean isUsedFlag() {
		return usedFlag;
	}
	public void setUsedFlag(boolean usedFlag) {
		this.usedFlag = usedFlag;
	}
	
}
