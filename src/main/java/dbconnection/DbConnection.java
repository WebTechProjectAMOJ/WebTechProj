package dbconnection;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class DbConnection {

    static String collectionName = null;
    static String dbname = "testdb";
    static String uri = "mongodb://mongo:example@localhost:27017/";

    public static Document findOne(String collectionName, Document searchQuery){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            return collection.find(searchQuery).first();
        }
    }

    public static ArrayList<Document> find(String collectionName, Document searchQuery){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            ArrayList<Document> soln = collection.find(searchQuery).into(new ArrayList<>());
            return soln;
        }
    }


}
