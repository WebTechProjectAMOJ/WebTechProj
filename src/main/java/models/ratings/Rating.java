package models.ratings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class Rating {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "feedback")
    private String feedback;
    @BsonProperty(value = "rating")
    private int rating;
    @BsonProperty(value = "consumer_id")
    private ObjectId consumer_id;

    public Rating() {}

    public Rating(Document doc) {
        this.id = doc.getObjectId("_id");
        this.feedback = doc.getString("feedback");
        this.rating = doc.getInteger("rating", 0);
        this.consumer_id = doc.getObjectId("consumer_id");
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ObjectId getConsumerId() {
        return consumer_id;
    }

    public void setConsumerId(ObjectId consumerId) {
        this.consumer_id = consumerId;
    }
}
