package io.brosoft.dao.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.JDBCType;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLField {

	String name() default "";
	
	JDBCType type();
}
