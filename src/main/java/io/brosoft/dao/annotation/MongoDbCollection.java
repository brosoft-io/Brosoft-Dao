package io.brosoft.dao.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.brosoft.dao.MongoDbInit;

@Retention(RUNTIME)
@Target(TYPE)
public @interface MongoDbCollection {

	String collection();
	
	String database();
	
	Class<? extends MongoDbInit> clientInitializer();
	
}
