package com.park.ssm.entity.type;

public enum ParkingPositionState {
	INACTIVE(-1),UNOCCUPIED(0),OCCUPIED(1),ReadyForOCCUPIED(2);
	
	private int ind;
	private ParkingPositionState(int ind) {
		this.ind=ind;
	}
	
	public int getInd() {
		return ind;
	}
	
	public static ParkingPositionState getValueByInd(int ind) {
		for(ParkingPositionState tmp:values()) {
			if(tmp.ind==ind) {
				return tmp;
			}
		}
		return null;
	}
	
}
