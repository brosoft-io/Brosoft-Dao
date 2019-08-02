package io.brosoft.dao.examples.sqlite;

import io.brosoft.dao.annotation.SQLField;

public class SqliteBeanExample {

	@SQLField(name = "first")
	private String firstName;
	@SQLField(name = "last")
	private String lastName;
	@SQLField
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
