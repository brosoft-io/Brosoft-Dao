package io.brosoft.dao.examples.postgresql;

import io.brosoft.dao.SQLDao;
import io.brosoft.dao.exceptions.ExecutionException;
import io.brosoft.dao.util.KeyPair;

public class PostgresqlExample {

    public static void main(String[] args) throws ExecutionException {
    	
    	SQLDao<PostgresqlBean> dao = new PostgresqlDaoExample();
    	
    	PostgresqlBean bean1 = new PostgresqlBean();
    	bean1.name = "Fox McCloud";
    	bean1.species = "Red Fox";
    	bean1.occupation = "Star Fox Team Leader";
    	
    	PostgresqlBean bean2 = new PostgresqlBean();
    	bean2.name = "Falco Lombardi";
    	bean2.species = "Avian";
    	bean2.occupation = "Star Fox Team Ace Pilot";
    	
    	System.out.println( "create " +  dao.create(bean1));
    	System.out.println( "delete " +  dao.delete(new KeyPair("name", bean1.name)));
    	
    	System.out.println( "create " +  dao.create(bean1));
    	System.out.println( "update " +  dao.update(bean2, new KeyPair("name", bean1.name)));
    	
    	for (PostgresqlBean bean : dao.readAll()) {
    		System.out.printf("name: %s, species: %s, occupation: %s\n", bean.name, bean.species, bean.occupation);
    	}
    	
    	System.out.println( "delete " +  dao.delete(new KeyPair("name", bean1.name)));
    	System.out.println( "delete " +  dao.delete(new KeyPair("name", bean2.name)));
    	
    }
}
