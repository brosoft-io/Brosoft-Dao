package io.brosoft.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import io.brosoft.dao.annotation.*;
import io.brosoft.dao.exceptions.BeanInstantiationException;
import io.brosoft.dao.exceptions.ExecutionException;
import io.brosoft.dao.exceptions.MissingDefaultConstructorException;
import io.brosoft.dao.util.BeanProcessor;
import io.brosoft.dao.util.KeyPair;
import org.bson.Document;

public abstract class MongoDao<T> extends AbstractDao<T> {

	protected static Map<String,MongoDatabase> dbMap = new HashMap<String, MongoDatabase>();
	protected MongoDatabase db;
	protected String collection;
	protected List<String> keyList;
	
	@SuppressWarnings("unchecked")
	public MongoDao() throws MissingDefaultConstructorException {
		MongoCollection documentCollection = this.getClass().getAnnotation(MongoCollection.class);
		this.collection = documentCollection.collection();
		this.clazz = (Class<T>) documentCollection.bean();
		this.db = dbMap.get(documentCollection.mongoInitializer().getCanonicalName());
		if (db == null) {
			try {
				db = documentCollection.mongoInitializer().getDeclaredConstructor().newInstance().initDatabase();
				dbMap.put(documentCollection.mongoInitializer().getCanonicalName(), db);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
				throw new MissingDefaultConstructorException(e);
			}
		}
		keyList = new ArrayList<String>();
		for (Field field : documentCollection.bean().getDeclaredFields()) {
			MongoField documentField = field.getAnnotation(MongoField.class);
			if (documentField != null) {
				String name = documentField.key();
				if (name.equals("")) {
					name = field.getName();
				}
				keyList.add(name);
			}
		}
	}
	
	public boolean create(T t) throws ExecutionException {
		boolean success = true;
		Document document = new Document();
		try {
			for (Field field : clazz.getDeclaredFields()) {
				MongoField documentField = field.getAnnotation(MongoField.class);
				if (documentField != null) {
					String key = documentField.key();
					if (key.equals("")) {
						key = field.getName();
					}
					field.setAccessible(true);
					document.append(key, field.get(t));
				}
			}
			db.getCollection(collection).insertOne(document);
		} catch (IllegalAccessException e) {
			success = false;
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
		return success;
	}

	public List<T> read(KeyPair... pairs) throws ExecutionException {
		List<T> list = new ArrayList<T>();
		Document filter = BeanProcessor.keyPairsToDocument(Arrays.asList(pairs));
		MongoCursor<Document> cursor = db.getCollection(collection).find(filter).iterator();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				T t = BeanProcessor.instantiate(clazz);
				for (Field field : clazz.getDeclaredFields()) {
					MongoField documentField = field.getAnnotation(MongoField.class);
					if (documentField != null) {
						String key = documentField.key();
						if (key.equals("")) {
							key = field.getName();
						}
						field.setAccessible(true);
						field.set(t, document.get(key));
					}
				}
				list.add(t);
			}
		} catch (BeanInstantiationException | IllegalAccessException e) {
			throw new ExecutionException(e);
		}
		return list;
	}

	@Override
	public List<T> readAll() throws ExecutionException {
		List<T> list = new ArrayList<T>();
		Document filter = new Document();
		MongoCursor<Document> cursor = db.getCollection(collection).find(filter).iterator();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				T t = BeanProcessor.instantiate(clazz);
				for (Field field : clazz.getDeclaredFields()) {
					MongoField documentField = field.getAnnotation(MongoField.class);
					if (documentField != null) {
						String key = documentField.key();
						if (key.equals("")) {
							key = field.getName();
						}
						field.setAccessible(true);
						field.set(t, document.get(key));
					}
				}
				list.add(t);
			}
		} catch (BeanInstantiationException | IllegalAccessException e) {
			throw new ExecutionException(e);
		}
		return list;
	}

	@Override
	public boolean update(T t, KeyPair... pairs) throws ExecutionException {
		boolean success = true;
		Document document = new Document();
		Document filter = BeanProcessor.keyPairsToDocument(Arrays.asList(pairs));
		try {
			for (Field field : clazz.getDeclaredFields()) {
				MongoField documentField = field.getAnnotation(MongoField.class);
				if (documentField != null) {
					String key = documentField.key();
					if (key.equals("")) {
						key = field.getName();
					}
					field.setAccessible(true);
					document.append(key, field.get(t));
				}
			}
			db.getCollection(collection).replaceOne(filter, document);
		} catch (IllegalAccessException e) {
			success = false;
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
		return success;
	}

	@Override
	public boolean delete(KeyPair... pairs) throws ExecutionException {
		Document filter = BeanProcessor.keyPairsToDocument(Arrays.asList(pairs));
		try {
			db.getCollection(collection).deleteMany(filter).wasAcknowledged();
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
		return true;
	}

}
