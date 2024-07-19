package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBClient {
    private static MongoClient mongoClient;

    private MongoDBClient() {
        // Private constructor to prevent instantiation
    }

    public static MongoClient getInstance() {
        if (mongoClient == null) {
            synchronized (MongoDBClient.class) {
                if (mongoClient == null) {
                    mongoClient = MongoClients.create("mongodb://localhost:27017");
                }
            }
        }
        return mongoClient;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
