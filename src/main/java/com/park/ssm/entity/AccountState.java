package com.park.ssm.entity;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

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
	
	@MappedJdbcTypes(value = JdbcType.TINYINT, includeNullJdbcType = true)
	public static class TypeHandler extends BaseTypeHandler<AccountState>{
		
		private Class<AccountState> classType;
		
		public TypeHandler() {}
		
		public TypeHandler(Class<AccountState> clazz) {
			if(clazz==null) throw new IllegalArgumentException("Type argument cannot be null"); 
			classType=clazz;
		}
		
		@Override
		public AccountState getNullableResult(ResultSet resultSet, String name) throws SQLException {
			// TODO Auto-generated method stub
			return AccountState.getValueByInd(resultSet.getInt(name));
		}

		@Override
		public AccountState getNullableResult(ResultSet resultSet, int index) throws SQLException {
			// TODO Auto-generated method stub
			return AccountState.getValueByInd(resultSet.getInt(index));
		}

		@Override
		public AccountState getNullableResult(CallableStatement statement, int index) throws SQLException {
			// TODO Auto-generated method stub
			return AccountState.getValueByInd(statement.getInt(index));
		}

		@Override
		public void setNonNullParameter(PreparedStatement statement, int index, AccountState value, JdbcType jdbcType)
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
