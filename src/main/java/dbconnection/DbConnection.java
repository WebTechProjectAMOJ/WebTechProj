package dbconnection;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.not;

public class DbConnection {
    static String dbname = "food_dispatch";
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

    public static InsertOneResult insertOne(String collectionName, Document object){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            return collection.insertOne(object);
        }
    }

    public static boolean deleteOne(String collectionName, Document searchQuery){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            return collection.deleteOne(searchQuery).wasAcknowledged();
        }
    }

    public static boolean deleteMany(String collectionName, Document searchQuery){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            return collection.deleteMany(searchQuery).wasAcknowledged();
        }
    }

    public static UpdateResult updateOne(String collectionName, Document searchQuery, BasicDBObject updateQuery){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            return collection.updateOne(searchQuery, updateQuery);
        }
    }

    public static boolean setOne(String collectionName, Document searchQuery, String key, String value){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document keyval = new Document(key, value);
            Document document = new Document();
            document.put("$set", keyval);
            return collection.updateOne(searchQuery, document).wasAcknowledged();
        }
    }

    public static boolean setOne(String collectionName, Document searchQuery, String key, Document value){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document keyval = new Document(key, value);
            Document document = new Document();
            document.put("$set", keyval);
            return collection.updateOne(searchQuery, document).wasAcknowledged();
        }
    }

    public static long getCount(String collectionName, Document searchQuery){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            return collection.countDocuments(searchQuery);
        }
    }

    public static ArrayList<String> distinct(String collectionName, String fieldName, Document searchQuery){
        ArrayList<String> result = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            DistinctIterable iter = null;
            if(searchQuery == null){
                iter = collection.distinct(fieldName, String.class);
            }
            else{
                iter = collection.distinct(fieldName, searchQuery, String.class);
            }
            for(Object i : iter){
                result.add((String) i);
            }
        }
        return result;
    }


    public static ArrayList<Document> find_with_not_filter(String collectionName, Document searchQuery){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(dbname);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            ArrayList<Document> soln = collection.find(not(searchQuery)).into(new ArrayList<>());
            return soln;
        }
    }

}
