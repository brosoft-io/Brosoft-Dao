package io.brosoft.dao.examples.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import io.brosoft.dao.MongoInit;

public class MongoInitExample implements MongoInit {

    @Override
    public MongoDatabase initDatabase() {
        MongoClient client = new MongoClient("localhost", 27017);
        return client.getDatabase("testDB");
    }
}
