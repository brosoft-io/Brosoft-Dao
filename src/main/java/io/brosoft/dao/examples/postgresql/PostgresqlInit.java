package io.brosoft.dao.examples.postgresql;

import io.brosoft.dao.SqlDbInit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PostgresqlInit implements SqlDbInit {
    @Override
    public Connection initDatabase() {
    	
    	Connection connection;
		try {
			String connectionUrl = "jdbc:postgresql://localhost:5432/test";
	    	Class.forName("org.postgresql.Driver");
	    	Properties props = new Properties();
	    	props.setProperty("user","postgres");
	    	props.setProperty("password", "");
			connection = DriverManager.getConnection(connectionUrl,"postgres","postgres");
			Statement statement = connection.createStatement();
			String createTableSql = "CREATE TABLE IF NOT EXISTS sampleTable ("
					+ "name TEXT PRIMARY KEY NOT NULL,"
					+ "species TEXT NOT NULL,"
					+ "occupation TEXT NOT NULL)";
			statement.execute(createTableSql);
			statement.close();
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
    	return connection;
    }
}
