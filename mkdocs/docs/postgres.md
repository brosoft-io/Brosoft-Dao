# PostgreSQL Integration

## SQL Connection Init Implementation

The first thing that is needed is to setup the connection to your [PostgreSQL](https://www.postgresql.org/) server and your application. What you need to do is make a Java class that implements `io.brosoft.dao.SqlDbInit`

```java
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
```

## Marking your Java Beans for DAO Usage

Next you should annotate your Java beans to be used by the DAO. If you do not supply a key for the field it will default to the Java field name.

```java
public class PostgresqlBean {
	
	@SQLField
	public String name;
	@SQLField
	public String species;
	@SQLField
	public String occupation;
	
}
```

## Making a DAO Class

This is extremely simple as all you need to do is simply annoatate both the MongoInit implementation, as well as the Java Bean that it is using. The DAO's param type will be the Type of the Java Bean

```java
@SQLTable(dbInitializer = PostgresqlInit.class, bean = PostgresqlBean.class, table = "sampleTable")
public class PostgresqlDaoExample extends SQLDao<PostgresqlBean> {
}
```

## Using the DAO

Usage of the DAO is straight forward and simple. Instantiate using the deafult constructor and perform CRUD operations.

```java
public class PostgresqlExample {

    public static void main(String[] args) throws ExecutionException {
    	
    	SQLDao<PostgresqlBean> dao = new PostgresqlDaoExample();
    	
    	PostgresqlBean bean1 = new PostgresqlBean();
    	bean1.name = "Fox McCloud";
    	bean1.species = "Red Fox";
    	bean1.occupation = "Star Fox Team Leader";
    	
    	PostgresqlBean bean2 = new PostgresqlBean();
    	bean2.name = "Falco Lombardi";
    	bean2.species = "Avian";
    	bean2.occupation = "Star Fox Team Ace Pilot";
    	
    	System.out.println( "create " +  dao.create(bean1));
    	System.out.println( "delete " +  dao.delete(new KeyPair("name", bean1.name)));
    	
    	System.out.println( "create " +  dao.create(bean1));
    	System.out.println( "update " +  dao.update(bean2, new KeyPair("name", bean1.name)));
    	
    	for (PostgresqlBean bean : dao.readAll()) {
    		System.out.printf("name: %s, species: %s, occupation: %s\n", bean.name, bean.species, bean.occupation);
    	}
    	
    	System.out.println( "delete " +  dao.delete(new KeyPair("name", bean1.name)));
    	System.out.println( "delete " +  dao.delete(new KeyPair("name", bean2.name)));
    	
    }
}
```
