package io.brosoft.dao.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.brosoft.dao.AbstractDao;

@Retention(RUNTIME)
@Target(TYPE)
public @interface DaoBean {
	
	Class<? extends AbstractDao<?>> dao();
}
