package io.brosoft.dao.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import io.brosoft.dao.annotation.SQLField;
import io.brosoft.dao.exceptions.BeanAccessException;
import io.brosoft.dao.exceptions.BeanInstantiationException;

public abstract class BeanProcessor {

	public static <T> List<KeyPair> SqlobjectToPairs(T t) {
		List<KeyPair> pairs = new ArrayList<KeyPair>();
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			SQLField sqliteField = field.getAnnotation(SQLField.class);
			if (sqliteField != null) {
				String name = sqliteField.name();
				if (name.equals("")) {
					name = field.getName();
				}
				try {
					pairs.add(new KeyPair(name, field.get(t)));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new BeanAccessException(e);
				}
			}
		}
		return pairs;
	}
	
	public static String getKeysAsString(List<KeyPair> pairs) {
		StringBuilder builder = new StringBuilder();
		Iterator<KeyPair> iterator = pairs.iterator();
		while (iterator.hasNext()) {
			builder.append(iterator.next().key);
			if (iterator.hasNext()) {
				builder.append(",");
			}
		}
		return builder.toString();
	}
	
	public static String getWhereAndArgsAsString(List<KeyPair> pairs) {
		StringBuilder builder = new StringBuilder();
		Iterator<KeyPair> iterator = pairs.iterator();
		while(iterator.hasNext()) {
			builder.append(String.format("%s = ?", iterator.next().key));
			if (iterator.hasNext()) {
				builder.append(" AND ");
			}
		}
		return builder.toString();
	}
	
	public static String getSetArgsAsString(List<KeyPair> pairs) {
		StringBuilder builder = new StringBuilder();
		Iterator<KeyPair> iterator = pairs.iterator();
		while(iterator.hasNext()) {
			builder.append(String.format("%s = ?", iterator.next().key));
			if (iterator.hasNext()) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}
	
	public static String getQmarksAsString(int numValues) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numValues; i++) {
			builder.append("?");
			if (i < numValues - 1) {
				builder.append(",");
			}
		}
		return builder.toString();
	}
	
	public static <T> T instantiate(Class<T> clazz) throws BeanInstantiationException {
		try {
			for (Field field : clazz.getFields()) {
				field.setAccessible(true);
			}
			return clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new BeanInstantiationException(e);
		}
	}

	public static Document keyPairsToDocument(List<KeyPair> pairs) {
		Document document = new Document();
		for (KeyPair pair : pairs) {
			document.append(pair.key, pair.value);
		}
		return document;
	}
	
}
