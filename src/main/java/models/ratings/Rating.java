package models.ratings;

import dbconnection.DbConnection;
import models.ui_util.ItemBoxUi;
import models.user.Consumer;
import models.user.User;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;


public class Rating {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "feedback")
    private String feedback;
    @BsonProperty(value = "rating")
    private int rating;
    @BsonProperty(value = "consumer_id")
    private ObjectId consumer_id;

    public Rating(Document document) {
        setId(document.getObjectId("_id"));
        setFeedback(document.getString("feedback"));
        setRating(document.getInteger("value"));
        setConsumer_id(document.getObjectId("consumer_id"));
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

    public ObjectId getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(ObjectId consumer_id) {
        this.consumer_id = consumer_id;
    }

    public Consumer get_author() {
        Document author_to_find = new Document("_id", this.getConsumer_id());
        Document found = DbConnection.findOne(
                "consumers",
                author_to_find
        );

        return new Consumer(found);
    }

}
