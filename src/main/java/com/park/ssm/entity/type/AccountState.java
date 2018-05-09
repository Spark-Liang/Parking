package com.park.ssm.entity.type;

public enum AccountState {
	UNPAID(-1),NORMAL(0),STOP(1);
	
	private int ind;
	private AccountState(int ind) {
		this.ind=ind;
	}
	
	public int getInd() {
		return ind;
	}
	
	public static AccountState getValueByInd(int ind) {
		for(AccountState tmp:values()) {
			if(tmp.ind==ind) {
				return tmp;
			}
		}
		return null;
	}
}
