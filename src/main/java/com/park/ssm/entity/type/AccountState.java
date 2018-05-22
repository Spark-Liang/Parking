package com.park.ssm.entity.type;

public enum AccountState {
	STOP(-1),NORMAL(0),UNPAY(1),PAYING(2);
	
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
