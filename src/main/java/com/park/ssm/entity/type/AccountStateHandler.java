package com.park.ssm.entity.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

@MappedJdbcTypes(value = JdbcType.TINYINT, includeNullJdbcType = true)
public class AccountStateHandler extends BaseTypeHandler<AccountState> {
	private Class<AccountState> classType;
	
	public AccountStateHandler() {}
	
	public AccountStateHandler(Class<AccountState> clazz) {
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
