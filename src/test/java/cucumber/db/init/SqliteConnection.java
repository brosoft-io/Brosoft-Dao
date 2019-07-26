package cucumber.db.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import cucumber.constants.CucumberConstants;
import io.brosoft.dao.SqlDbInit;

public class SqliteConnection implements SqlDbInit {

	@Override
	public Connection initDatabase() {
		Connection connection;
		Statement stmt = null;
	      try {
	         Class.forName("org.sqlite.JDBC");
	         connection = DriverManager.getConnection(CucumberConstants.SQLITE_URI);
	         stmt = connection.createStatement();
	         String createAnimeTableSql = "CREATE TABLE IF NOT EXISTS " + CucumberConstants.ANIME_TABLE + " (" 
	        		+ CucumberConstants.ANIME_TITLE + " TEXT PRIMARY KEY NOT NULL,"
	        		+ CucumberConstants.ANIME_YEAR + " INT NOT NULL,"
    		 		+ CucumberConstants.ANIME_EPS + " INT NOT NULL"
	 				+ ");"; 
	         stmt.executeUpdate(createAnimeTableSql);
	         stmt.close();
	      } catch ( Exception e ) {
	         throw new RuntimeException(e);
	      }
		return connection;
	}

}
