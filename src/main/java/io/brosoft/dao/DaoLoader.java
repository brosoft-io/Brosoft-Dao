package io.brosoft.dao;

import io.brosoft.dao.annotation.DocumentCollection;
import io.brosoft.dao.annotation.SQLTable;
import io.brosoft.dao.exceptions.DaoInitException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DaoLoader {

	List<String> packages;
	Reflections reflections;
	
	public DaoLoader() {
		packages = new ArrayList<String>();
	}
	
	public static DaoLoader newLoader(String pkg) {
		return new DaoLoader().addDaoPackage(pkg);
	}
	
	public DaoLoader addDaoPackage(String pkg) {
		packages.add(pkg);
		return this;
	}
	
	public void load() throws DaoInitException {
		reflections = new Reflections(packages.toArray());
		Set<Class<?>> sqlDaos = reflections.getTypesAnnotatedWith(SQLTable.class);
		Set<Class<?>> collectionDaos = reflections.getTypesAnnotatedWith(DocumentCollection.class);
		try {
			for (Class<?> clazz : sqlDaos) {
				clazz.getDeclaredConstructor().newInstance();
			}
			for (Class<?> clazz : collectionDaos) {
				clazz.getDeclaredConstructor().newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new DaoInitException(e);
		}
	}

	
	
	
}
