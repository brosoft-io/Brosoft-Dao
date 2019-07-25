package io.brosoft.dao.examples.mongodb;

import io.brosoft.dao.exceptions.ExecutionException;
import io.brosoft.dao.util.KeyPair;

import java.util.List;

public class MongoExample {

    public static void main(String[] args) throws ExecutionException {
        MongoDao dao = new MongoDao();

        MongoBean bean1 = new MongoBean();
        bean1.setTitle("akira");
        bean1.setGenre("sci-fi");
        bean1.setYear(1995);

        MongoBean bean2 = new MongoBean();
        bean2.setTitle("serial experiments lain");
        bean2.setGenre("sci-fi");
        bean2.setYear(1995);

        System.out.println("create " + dao.create(bean1));
        System.out.println("delete " + dao.delete(new KeyPair("name", bean1.getTitle())));

        System.out.println("create " + dao.create(bean1));
        System.out.println("update " + dao.update(bean2, new KeyPair("name", bean1.getTitle())));

        List<MongoBean> beans = dao.readAll();
        for (MongoBean bean : beans) {
            System.out.println(String.format("name: %s, genre: %s, date: %s", bean.getTitle(), bean.getGenre(), bean.getYear()));
        }

        System.out.println("delete " + dao.delete(new KeyPair("name", bean1.getTitle())));
        System.out.println("delete " + dao.delete(new KeyPair("name", bean2.getTitle())));

    }

}
