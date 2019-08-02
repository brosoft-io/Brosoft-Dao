package io.brosoft.dao;

public abstract class AbstractDao<T> implements Crud<T> {

	protected Class<T> clazz = null;
}
