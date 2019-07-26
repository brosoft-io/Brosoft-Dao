package cucumber.db.init;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import cucumber.constants.CucumberConstants;
import io.brosoft.dao.MongoInit;

public class MongoConnection implements MongoInit {
	
	@Override
	public MongoDatabase initDatabase() {
		MongoClient client;
		String env = System.getenv(CucumberConstants.ENV);
		if (env != null && env.equals(CucumberConstants.ENV_VALUE)) { 
			client = new MongoClient(CucumberConstants.MONGO_GITLAB_URI, CucumberConstants.MONGO_PORT);
		} else {
			client = new MongoClient(CucumberConstants.MONGO_URI, CucumberConstants.MONGO_PORT);
		}
		
		// TODO Auto-generated method stub
		return client.getDatabase("test");
	}

}
