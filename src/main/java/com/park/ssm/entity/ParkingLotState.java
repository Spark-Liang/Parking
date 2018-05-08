package com.park.ssm.entity;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

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
	
	@MappedJdbcTypes(value = JdbcType.TINYINT, includeNullJdbcType = true)
	public static class TypeHandler extends BaseTypeHandler<ParkingLotState>{
		
		private Class<ParkingLotState> classType;
		
		public TypeHandler() {}
		
		public TypeHandler(Class<ParkingLotState> clazz) {
			if(clazz==null) throw new IllegalArgumentException("Type argument cannot be null"); 
			classType=clazz;
		}
		
		@Override
		public ParkingLotState getNullableResult(ResultSet resultSet, String name) throws SQLException {
			// TODO Auto-generated method stub
			return ParkingLotState.getValueByInd(resultSet.getInt(name));
		}

		@Override
		public ParkingLotState getNullableResult(ResultSet resultSet, int index) throws SQLException {
			// TODO Auto-generated method stub
			return ParkingLotState.getValueByInd(resultSet.getInt(index));
		}

		@Override
		public ParkingLotState getNullableResult(CallableStatement statement, int index) throws SQLException {
			// TODO Auto-generated method stub
			return ParkingLotState.getValueByInd(statement.getInt(index));
		}

		@Override
		public void setNonNullParameter(PreparedStatement statement, int index, ParkingLotState value, JdbcType jdbcType)
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
