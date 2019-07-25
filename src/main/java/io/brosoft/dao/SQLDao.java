package io.brosoft.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.brosoft.dao.annotation.SQLField;
import io.brosoft.dao.annotation.SQLTable;
import io.brosoft.dao.exceptions.BeanInstantiationException;
import io.brosoft.dao.exceptions.ExecutionException;
import io.brosoft.dao.exceptions.MissingDefaultConstructorException;
import io.brosoft.dao.util.BeanProcessor;
import io.brosoft.dao.util.KeyPair;

public abstract class SQLDao<T> extends AbstractDao<T> {
	
	protected static Connection connection;
	protected String table;
	protected List<String> columnList;
	
	/**
	 * Will throw runtime Error exception if missing Annotation
	 */
	@SuppressWarnings("unchecked")
	public SQLDao() throws MissingDefaultConstructorException {
		SQLTable sqlTable = this.getClass().getAnnotation(SQLTable.class);
		this.table = sqlTable.table();
		this.clazz = (Class<T>) sqlTable.bean();
		if (connection == null) {
			try {
				connection = sqlTable.dbInitializer().getDeclaredConstructor().newInstance().initDatabase();
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
				throw new MissingDefaultConstructorException(e);
			}
		}
		columnList = new ArrayList<String>();
		for (Field field : sqlTable.bean().getDeclaredFields()) {
			SQLField sqlField = field.getAnnotation(SQLField.class);
			if (sqlField != null) {
				String name = sqlField.name();
				if (name.equals("")) {
					name = field.getName();
				}
				columnList.add(name);
			}
		}
	}

	@Override
	public boolean create(T t) throws ExecutionException {
		String templateStatement = "INSERT INTO %s (%s) VALUES (%s)";
		boolean sucess = false;
		List<KeyPair> pairs = BeanProcessor.SqlobjectToPairs(t);
		templateStatement = String.format(templateStatement, table, BeanProcessor.getKeysAsString(pairs),
				BeanProcessor.getQmarksAsString(pairs.size()));
		System.out.println(templateStatement);
		try (PreparedStatement statement = connection.prepareStatement(templateStatement)) {
			for (int i = 0; i < pairs.size(); i++) {
				statement.setObject(i + 1, pairs.get(i).value);
			}
			System.out.println(statement.toString());
			sucess = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new ExecutionException(e);
		}
		return sucess;
	}

	@Override
	public List<T> read(KeyPair... pairs) throws ExecutionException {
		List<KeyPair> keyPairs = Arrays.asList(pairs);
		List<T> list = new ArrayList<T>();
		String templateStatement = String.format("SELECT * FROM %s WHERE %s", 
				table, BeanProcessor.getWhereAndArgsAsString(keyPairs));
		System.out.println(templateStatement);
		try (PreparedStatement statement = connection.prepareStatement(templateStatement)){
			for (int i = 0; i < keyPairs.size(); i++) {
				statement.setObject(i + 1, keyPairs.get(i).value);
			}
			System.out.println(statement.toString());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				T t = BeanProcessor.instantiate(clazz);
				for (Field field : clazz.getDeclaredFields()) {
					SQLField sqlField = field.getAnnotation(SQLField.class);
					String name = sqlField.name();
					if (name.equals("")) {
						name = field.getName();
					}
					field.setAccessible(true);
					field.set(t, resultSet.getObject(name));
				}
				list.add(t);
			}
			resultSet.close();
		} catch (SQLException | BeanInstantiationException | IllegalArgumentException | IllegalAccessException e) {
			throw new ExecutionException(e);
		}
		return list;
	}

	@Override
	public List<T> readAll() throws ExecutionException {
		String templateStatement = String.format("SELECT * FROM %s", table);
		List<T> list = new ArrayList<T>();
		System.out.println(templateStatement);
		try (PreparedStatement statement = connection.prepareStatement(templateStatement);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				T t = BeanProcessor.instantiate(clazz);
				for (Field field : clazz.getDeclaredFields()) {
					SQLField sqlField = field.getAnnotation(SQLField.class);
					String name = sqlField.name();
					if (name.equals("")) {
						name = field.getName();
					}
					field.setAccessible(true);
					field.set(t, resultSet.getObject(name));
				}
				list.add(t);
			}
		} catch (SQLException | BeanInstantiationException | IllegalArgumentException | IllegalAccessException e) {
			throw new ExecutionException(e);
		}
		return list;
	}

	@Override
	public boolean update(T t, KeyPair... pairs) throws ExecutionException {
		List<KeyPair> keyPairs = Arrays.asList(pairs);
		List<KeyPair> tPairs = BeanProcessor.SqlobjectToPairs(t);
		String templateStatement = String.format("UPDATE %s SET %s WHERE %s", table, 
				BeanProcessor.getSetArgsAsString(tPairs), BeanProcessor.getWhereAndArgsAsString(keyPairs));
		boolean sucess = false;
		System.out.println(templateStatement);
		try (PreparedStatement statement = connection.prepareStatement(templateStatement)) {
			int i = 0;
			for (; i < tPairs.size(); i++) {
				statement.setObject(i + 1, tPairs.get(i).value);
			}
			for (; i < tPairs.size() + keyPairs.size(); i++) {
				statement.setObject(i + 1, keyPairs.get(i - tPairs.size()).value);
			}
			System.out.println();
			sucess = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new ExecutionException(e);
		}
		return sucess;
	}

	@Override
	public boolean delete(KeyPair... pairs) throws ExecutionException {
		List<KeyPair> keyPairs = Arrays.asList(pairs);
		String templateStatement = String.format("DELETE FROM %s WHERE %s", 
				table, BeanProcessor.getWhereAndArgsAsString(keyPairs));
		boolean sucess = false;
		System.out.println(templateStatement);
		try (PreparedStatement statement = connection.prepareStatement(templateStatement)) {
			for (int i = 0; i < keyPairs.size(); i++) {
				statement.setObject(i + 1, keyPairs.get(i).value);
			}
			sucess = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new ExecutionException(e);
		}
		return sucess;
	}

}
