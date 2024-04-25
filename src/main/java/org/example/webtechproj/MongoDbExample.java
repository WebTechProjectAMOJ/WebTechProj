package org.example.webtechproj;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dbconnection.DbConnection;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class MongoDbExample {
    public static void main( String[] args ) {
        Document searchQuery = new Document();
        searchQuery.put("x", 1);
        ArrayList<Document> doc = DbConnection.find("testData", searchQuery);
        System.out.println(doc);
    }
}
