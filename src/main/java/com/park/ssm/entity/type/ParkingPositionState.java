package com.park.ssm.entity.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

public enum ParkingPositionState {
	INACTIVE(-1),UNOCCUPIED(0),OCCUPIED(1);
	
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
