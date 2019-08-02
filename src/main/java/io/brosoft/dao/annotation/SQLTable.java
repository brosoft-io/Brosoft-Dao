package io.brosoft.dao.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.Connection;

import io.brosoft.dao.DatabaseInit;

@Retention(RUNTIME)
@Target(TYPE)
public @interface SQLTable {

	String table();
	
	Class<?> bean();
	
	Class<? extends DatabaseInit<? extends Connection>> dbInitializer();
}
