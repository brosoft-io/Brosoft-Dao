package io.brosoft.dao.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.JDBCType;

@Retention(RUNTIME)
@Target(FIELD)
public @interface PostgresqlField {

	String name() default "";
	
	JDBCType type();
}
