<<<<<<< HEAD
# SQLite Integration

## SQL Connection Init Implementation

The first thing that is needed is to setup the connection to your [Sqlite](https://sqlite.org/index.html) database and your application. What you need to do is make a Java class that implements `io.brosoft.dao.SqlDbInit`

```java
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
	         throw new RuntimeException(e);
	      }
	    return c;
	}

}
```

## Marking your Java Beans for DAO Usage

Next you should annotate your Java beans to be used by the DAO. If you do not supply a key for the field it will default to the Java field name.

```java
public class SqliteBeanExample {

	@SQLField(name = "first")
	private String firstName;
	@SQLField(name = "last")
	private String lastName;
	@SQLField
    private int age;
    
}
```

## Making a DAO Class

This is extremely simple as all you need to do is simply annoatate both the MongoInit implementation, as well as the Java Bean that it is using. The DAO's param type will be the Type of the Java Bean

```java
@SQLTable(table = "TESTTABLE", bean = SqliteBeanExample.class, dbInitializer = SqliteInitExmple.class)
public class SqliteDaoExample extends SQLDao<SqliteBeanExample> {

}
```

## Using the DAO

Usage of the DAO is straight forward and simple. Instantiate using the deafult constructor and perform CRUD operations.

```java
public class SqliteExample {

	public static void main(String[] args) throws ExecutionException {

		SQLDao<SqliteBeanExample> dao = new SqliteDaoExample();
		
		SqliteBeanExample bean1 = new SqliteBeanExample();
		bean1.setFirstName("john");
		bean1.setLastName("snow");
		bean1.setAge(22);
		
		SqliteBeanExample bean2 = new SqliteBeanExample();
		bean2.setFirstName("obi");
		bean2.setLastName("wan");
		bean2.setAge(57);
		
		System.out.println(dao.create(bean1));
		System.out.println(dao.delete(new KeyPair("first", bean1.getFirstName())));
		System.out.println(dao.create(bean1));
		System.out.println(dao.update(bean2, new KeyPair("first",  bean1.getFirstName())));
		dao.readAll().forEach((bean) -> {
			System.out.println(String.format("F: %s L: %s A: %s", bean.getFirstName(), bean.getLastName(), bean.getAge()));
		});
		System.out.println(dao.delete(new KeyPair("first",  bean1.getFirstName())));
		System.out.println(dao.delete(new KeyPair("first",  bean2.getFirstName())));
	}

}
```
=======
# SQLite Integration
>>>>>>> 7d3dcf7cc11ab570732c1469622f221c2cb3eaf0
