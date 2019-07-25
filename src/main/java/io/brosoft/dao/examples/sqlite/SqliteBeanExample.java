package io.brosoft.dao.examples.sqlite;

import java.sql.JDBCType;

import io.brosoft.dao.annotation.SQLField;

public class SqliteBeanExample {

	@SQLField(name = "first", type = JDBCType.VARCHAR)
	private String firstName;
	@SQLField(name = "last", type = JDBCType.VARCHAR)
	private String lastName;
	@SQLField(type = JDBCType.INTEGER)
	private int age;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
