package io.brosoft.dao.examples.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.brosoft.dao.MongoDbInit;

public class MongoInit implements MongoDbInit {

    @Override
    public MongoDatabase initDatabase() {
        MongoClient client = new MongoClient("localhost", 27017);
        return client.getDatabase("testDB");
    }
}
