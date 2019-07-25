package io.brosoft.dao.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.mongodb.client.MongoDatabase;
import io.brosoft.dao.DatabaseInit;

@Retention(RUNTIME)
@Target(TYPE)
public @interface DocumentCollection {

	String collection();
	
	Class<?> bean();
	
	Class<? extends DatabaseInit<? extends MongoDatabase>> collectionInitializer();
}
