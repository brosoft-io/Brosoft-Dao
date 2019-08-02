# Welcome to Brosoft DAO

## What is Brosoft DAO

Brosoft DAO is a Java library that allows you to quickly connect your project to a database
without the hassle of having to write all the DAO classes to translate between Java Beans and your database of choice.

## How Does it Work

There are 3 requirements at minimum to use Brosoft DAO that are generally the same regardless of the database that you are using.

* Implement the DB Connection Initializer
* Annotate a Class to be the DAO for a Java Bean
* Annotate the fields of the Java Bean for use in the DAO
