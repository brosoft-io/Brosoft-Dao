package io.brosoft.dao.examples.sqlite;

import io.brosoft.dao.DaoLoader;
import io.brosoft.dao.SQLDao;
import io.brosoft.dao.exceptions.DaoInitException;
import io.brosoft.dao.exceptions.ExecutionException;
import io.brosoft.dao.util.KeyPair;

public class SqliteExample {

	public static void main(String[] args) throws DaoInitException, ExecutionException {

//		DaoLoader.newLoader("io.brosoft.dao.examples.sqlite").load();
		
		SQLDao<SqliteBeanExample> dao = new SqliteDaoExample();
		
		SqliteBeanExample bean1 = new SqliteBeanExample();
		bean1.setFirstName("john");
		bean1.setLastName("snow");
		bean1.setAge(35);
		SqliteBeanExample bean2 = new SqliteBeanExample();
		bean2.setFirstName("obi");
		bean2.setLastName("wan");
		bean2.setAge(56);
		
		System.out.println(dao.create(bean1));
		System.out.println(dao.delete(new KeyPair("first", bean1.getFirstName())));
		System.out.println(dao.create(bean1));
		System.out.println(dao.update(bean2, new KeyPair("first",  bean1.getFirstName())));
		dao.readAll().forEach((bean) -> {
			System.out.println(String.format("F: %s L: %s A: %s", bean.getFirstName(), bean.getLastName(), bean.getAge()));
		});
		System.out.println(dao.delete(new KeyPair("first",  bean1.getFirstName())));
		System.out.println(dao.delete(new KeyPair("first",  bean2.getFirstName())));
	}

}
