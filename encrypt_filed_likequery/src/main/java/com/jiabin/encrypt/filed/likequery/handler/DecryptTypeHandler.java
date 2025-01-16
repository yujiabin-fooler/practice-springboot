package com.jiabin.encrypt.filed.likequery.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.stereotype.Component;

import com.jiabin.encrypt.filed.likequery.utils.SecretComponent;

@Component
@MappedJdbcTypes(JdbcType.VARCHAR)
public class DecryptTypeHandler extends BaseTypeHandler<String> {

  private static final Set<String> COLUMNS =  Set.of("id_no", "phone") ;
  
  private final SecretComponent sc ;
  public DecryptTypeHandler(SecretComponent sc) {
    this.sc = sc ;
  }
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter) ;
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String encrypted = rs.getString(columnName);
		if (COLUMNS.contains(columnName)) {
		  return decrypt(encrypted) ;
		}
		return encrypted ;
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String encrypted = rs.getString(columnIndex);
		return decrypt(encrypted) ;
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String encrypted = cs.getString(columnIndex);
		return decrypt(encrypted) ;
	}

	private String decrypt(String cipherText) {
		return this.sc.decrypt(cipherText) ;
	}
}