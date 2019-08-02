package io.brosoft.dao.examples.postgresql;

import io.brosoft.dao.annotation.SQLField;

public class PostgresqlBean {
	
	@SQLField
	public String name;
	@SQLField
	public String species;
	@SQLField
	public String occupation;
	
}
