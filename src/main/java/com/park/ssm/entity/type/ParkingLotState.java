package com.park.ssm.entity.type;

public enum ParkingLotState {
	INACTIVE(-1),ACTIVE(0);
	
	private int ind;
	private ParkingLotState(int ind) {
		this.ind=ind;
	}
	
	public int getInd() {
		return ind;
	}
	
	public static ParkingLotState getValueByInd(int ind) {
		for(ParkingLotState tmp:values()) {
			if(tmp.ind==ind) {
				return tmp;
			}
		}
		return null;
	}
	
	
}
