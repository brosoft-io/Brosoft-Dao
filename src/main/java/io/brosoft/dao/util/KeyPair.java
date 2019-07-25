package io.brosoft.dao.util;

import java.sql.JDBCType;

public class KeyPair {

	public String key;
	public Object value;
	public JDBCType type;
	
	public KeyPair(String key, Object value) {
		this.key = key;
		this.value = value;
		this.type = JDBCType.VARCHAR;
	}
	
	public KeyPair(String key, Object value, JDBCType type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}
	
}
