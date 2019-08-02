package cucumber.db.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import cucumber.constants.CucumberConstants;
import io.brosoft.dao.SqlDbInit;

public class PostgresqlConnection implements SqlDbInit {

	@Override
	public Connection initDatabase() {
		Connection connection;
		Statement stmt = null;
		Properties props = new Properties();
	      try {
	         Class.forName("org.postgresql.Driver");
	         String env = System.getenv(CucumberConstants.ENV);
	         if (env != null && env.equals(CucumberConstants.ENV_VALUE)) {
	        	 props.setProperty("user","gitlab");
	        	 props.setProperty("password", "gitlab");
	        	 connection = DriverManager.getConnection(CucumberConstants.POSTGRES_GITLAB_URI, props);
	         } else {
	        	 props.setProperty("user","postgres");
	        	 props.setProperty("password", "postgres");
	        	 connection = DriverManager.getConnection(CucumberConstants.POSTGRES_URI, props);
	         }
	         
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
