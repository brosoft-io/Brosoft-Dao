# Welcome to Brosoft DAO

## What is Brosoft DAO

Brosoft DAO is a Java library that allows you to quickly connect your project to a database
without the hassle of having to write all the DAO classes to translate between Java Beans and your database of choice.

## How Does it Work

There are 3 requirements at minimum to use Brosoft DAO that are generally the same regardless of the database that you are using.

* Implement the DB Connection Initializer
* Annotate a Class to be the DAO for a Java Bean
* Annotate the fields of the Java Bean for use in the DAO

## Database-Specific Documentation

* #### [Mongo](mongo)

* #### [PostgreSQL](postgres)

* #### [SQLite](sqlite)

## DAO methods

```java
/**
 * Creates a new Record in the Database
 * @param t
 * @return
 * @throws ExecutionException
 */
boolean create(T t) throws ExecutionException
```

```java
/**
 * Reads all records that match the given set of key pairs
 * @param pairs
 * @return
 * @throws ExecutionException
 */
List<T> read(KeyPair... pairs) throws ExecutionException
```

```java
/**
 * Reads all records
 * @return
 * @throws ExecutionException
 */
List<T> readAll() throws ExecutionException
```

```java
/**
 * Replaces record matching key pairs with new record
 * @param t
 * @param pairs
 * @return
 * @throws ExecutionException
 */
boolean update(T t, KeyPair... pairs) throws ExecutionException
```

```java
/**
 * Deletes all records that match the given key pairs
 * @param pairs
 * @return
 * @throws ExecutionException
 */
boolean delete(KeyPair... pairs) throws ExecutionException
```
