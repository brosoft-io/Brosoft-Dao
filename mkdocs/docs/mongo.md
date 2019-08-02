<<<<<<< HEAD
# MongoDB Integration

## MongoDatabase Init Implementation

The first thing that is needed is to setup the connection to your [MongoDB](https://www.mongodb.com/what-is-mongodb) server and your application. What you need to do is make a Java class that implements `io.brosoft.dao.MongoInit`

```java
public class MongoInitExample implements MongoInit {

    @Override
    public MongoDatabase initDatabase() {
        MongoClient client = new MongoClient("localhost", 27017);
        return client.getDatabase("testDB");
    }
}
```

## Marking your Java Beans for DAO Usage

Next you should annotate your Java beans to be used by the DAO. If you do not supply a key for the field it will default to the Java field name.

```java
public class MongoBean {

    @MongoField(key = "name")
    private String title;
    @MongoField
    private String genre;
    @MongoField(key = "release-year")
    private int year;
}
```

## Making a DAO Class

This is extremely simple as all you need to do is simply annoatate both the MongoInit implementation, as well as the Java Bean that it is using. The DAO's param type will be the Type of the Java Bean

```java
@MongoCollection(bean = MongoBean.class, collection = "testCollection", mongoInitializer = MongoInitExample.class)
public class MongoDaoExample extends MongoDao<MongoBean> {
}
```

## Using the DAO

Usage of the DAO is straight forward and simple. Instantiate using the deafult constructor and perform CRUD operations.

```java
public class MongoExample {

    public static void main(String[] args) throws ExecutionException {
        MongoDaoExample dao = new MongoDaoExample();

        MongoBean bean1 = new MongoBean();
        bean1.setTitle("akira");
        bean1.setGenre("drama/thriller");
        bean1.setYear(1988);

        MongoBean bean2 = new MongoBean();
        bean2.setTitle("serial experiments lain");
        bean2.setGenre("sci-fi");
        bean2.setYear(1995);

        System.out.println("create " + dao.create(bean1));
        System.out.println("delete " + dao.delete(new KeyPair("name", bean1.getTitle())));

        System.out.println("create " + dao.create(bean1));
        System.out.println("update " + dao.update(bean2, new KeyPair("name", bean1.getTitle())));

        List<MongoBean> beans = dao.readAll();
        for (MongoBean bean : beans) {
            System.out.println(String.format("name: %s, genre: %s, date: %s", bean.getTitle(), bean.getGenre(), bean.getYear()));
        }

        System.out.println("delete " + dao.delete(new KeyPair("name", bean1.getTitle())));
        System.out.println("delete " + dao.delete(new KeyPair("name", bean2.getTitle())));

    }

}
```
=======
# MongoDB Integration
>>>>>>> 7d3dcf7cc11ab570732c1469622f221c2cb3eaf0
