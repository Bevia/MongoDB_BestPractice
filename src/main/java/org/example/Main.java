package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

//if you get Caused by: java.net.ConnectException: Connection refused (Connection refused)
//do this: brew services start mongodb-community
//or this: brew services restart mongodb/brew/mongodb-community

public class Main {

    public static void main(String[] args) {
        // Get MongoDB client instance
        MongoClient mongoClient = MongoDBClient.getInstance();

        // Access database
        MongoDatabase database = mongoClient.getDatabase("mydb");

        // Access collection
        MongoCollection<Document> collection = database.getCollection("mycollection");

        // Create POJO
        Person person1 = new Person("John Doe", 29, "New York");
        Person person2 = new Person("Vincent Bevia", 55, "New York");

        // Convert POJO to Document
        Document document1 = new Document("name", person1.getName())
                .append("age", person1.getAge())
                .append("city", person1.getCity());

        Document document2 = new Document("name", person2.getName())
                .append("age", person2.getAge())
                .append("city", person2.getCity());

        // Insert documents into the collection
        collection.insertOne(document1);
        collection.insertOne(document2);

        // Retrieve and print the documents
        Document myDoc1 = collection.find(new Document("name", "John Doe")).first();
        if (myDoc1 != null) {
            System.out.println("Retrieved document 1: " + myDoc1.toJson());
        }

        Document myDoc2 = collection.find(new Document("name", "Vincent Bevia")).first();
        if (myDoc2 != null) {
            System.out.println("Retrieved document 2: " + myDoc2.toJson());
        }

        // Close the connection
        MongoDBClient.close();
    }
}
