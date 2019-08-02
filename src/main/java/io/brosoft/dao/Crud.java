package io.brosoft.dao;

import java.util.List;

import io.brosoft.dao.exceptions.ExecutionException;
import io.brosoft.dao.util.KeyPair;

public interface Crud<T> {

	/**
	 * Creates a new Record in the Database
	 * @param t
	 * @return
	 * @throws ExecutionException
	 */
	boolean create(T t) throws ExecutionException;

	/**
	 * Reads all records that match the given set of key pairs
	 * @param pairs
	 * @return
	 * @throws ExecutionException
	 */
	List<T> read(KeyPair... pairs) throws ExecutionException;

	/**
	 * Reads all records
	 * @return
	 * @throws ExecutionException
	 */
	List<T> readAll() throws ExecutionException;

	/**
	 * Replaces record matching key pairs with new record
	 * @param t
	 * @param pairs
	 * @return
	 * @throws ExecutionException
	 */
	boolean update(T t, KeyPair... pairs) throws ExecutionException;

	/**
	 * Deletes all records that match the given key pairs
	 * @param pairs
	 * @return
	 * @throws ExecutionException
	 */
	boolean delete(KeyPair... pairs) throws ExecutionException;
}
