package models.ratings;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.sql.Driver;
import java.util.ArrayList;

public class Rating {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "feedback")
    private String feedback;
    @BsonProperty(value = "rating")
    private int rating;
    @BsonProperty(value = "consumer_id")
    private ObjectId consumer_id;
}
