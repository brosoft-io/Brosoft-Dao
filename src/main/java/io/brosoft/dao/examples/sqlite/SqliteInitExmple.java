package io.brosoft.dao.examples.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import io.brosoft.dao.SqlDbInit;

public class SqliteInitExmple implements SqlDbInit {

	@Override
	public Connection initDatabase() {
		Connection c = null;
		Statement stmt = null;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         System.out.println("Opened database successfully");
	         stmt = c.createStatement();
	         String sql = "CREATE TABLE IF NOT EXISTS TESTTABLE "
	         		+ "(first TEXT PRIMARY KEY NOT NULL,"
	         		+ "last TEXT NOT NULL,"
	         		+ "age INT NOT NULL)"; 
	         stmt.executeUpdate(sql);
	         stmt.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	    return c;
	}

}
