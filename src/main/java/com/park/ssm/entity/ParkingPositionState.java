package com.park.ssm.entity;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

public enum ParkingPositionState {
	INACTIVE(-1),OCCUPIED(0),UNOCCUPIED(1);
	
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
	
	@MappedJdbcTypes(value = JdbcType.TINYINT, includeNullJdbcType = true)
	public static class TypeHandler extends BaseTypeHandler<ParkingPositionState>{
		
		private Class<ParkingPositionState> classType;
		
		public TypeHandler() {}
		
		public TypeHandler(Class<ParkingPositionState> clazz) {
			if(clazz==null) throw new IllegalArgumentException("Type argument cannot be null"); 
			classType=clazz;
		}
		
		@Override
		public ParkingPositionState getNullableResult(ResultSet resultSet, String name) throws SQLException {
			// TODO Auto-generated method stub
			return ParkingPositionState.getValueByInd(resultSet.getInt(name));
		}

		@Override
		public ParkingPositionState getNullableResult(ResultSet resultSet, int index) throws SQLException {
			// TODO Auto-generated method stub
			return ParkingPositionState.getValueByInd(resultSet.getInt(index));
		}

		@Override
		public ParkingPositionState getNullableResult(CallableStatement statement, int index) throws SQLException {
			// TODO Auto-generated method stub
			return ParkingPositionState.getValueByInd(statement.getInt(index));
		}

		@Override
		public void setNonNullParameter(PreparedStatement statement, int index, ParkingPositionState value, JdbcType jdbcType)
				throws SQLException {
			// TODO Auto-generated method stub
			if(value==null) {
				statement.setNull(index, jdbcType.TYPE_CODE);
			}else {
				statement.setInt(index, value.getInd());
			}
		}
		
	}
}
