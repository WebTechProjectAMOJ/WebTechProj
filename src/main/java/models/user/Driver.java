package models.user;

import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import models.ratings.Rating;
import models.ui_util.RatingBoxUi;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;


public class Driver extends User implements login {
    @BsonProperty(value = "first_name")
    private String firstName;
    @BsonProperty(value = "tools")
    private ArrayList<ObjectId> tools;
    @BsonProperty(value = "ratings")
    private ArrayList<ObjectId> ratings;
    @BsonProperty(value = "current_pos")
    private Object currentPos;

    public Driver(Document document) {
        super(document, "driver");
        setFirstName(document.getString("first_name"));
        setTools((ArrayList<ObjectId>) document.get("tools"));
        setRatings((ArrayList<ObjectId>) document.get("ratings"));
        setCurrentPos(document.get("current_pos"));
    }

    public Driver(String username,
                  String password,
                  String name,
                  String email,
                  ArrayList<ObjectId> orders,
                  String firstName,
                  ArrayList<ObjectId> tools,
                  ArrayList<ObjectId> ratings,
                  Object current_pos) {
        super(username, password, name, email, orders, "driver");
        setFirstName(firstName);
        setTools(tools);
        setRatings(ratings);
        setCurrentPos(current_pos);
    }

    public boolean write() {
        Document doc = this.toDocument();
        doc.put("current_pos", getCurrentPos());
        doc.put("ratings", getRatings());
        doc.put("tools", getTools());
        doc.put("first_name", getFirstName());
        InsertOneResult written = DbConnection.insertOne("drivers", doc);
        BsonObjectId id = (BsonObjectId) written.getInsertedId();
        this.setId(id.getValue());
        return written.wasAcknowledged();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ArrayList<ObjectId> getTools() {
        return tools;
    }

    public void setTools(ArrayList<ObjectId> tools) {
        this.tools = tools;
    }

    public ArrayList<ObjectId> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<ObjectId> ratings) {
        this.ratings = ratings;
    }

    public Object getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Object currentPos) {
        this.currentPos = currentPos;
    }

    public JsonObject getJsonCurrentPos() {
        org.bson.Document doc = (Document) this.currentPos;
        return new JsonObject(doc.toJson());
    }

    public Document getDocumentCurrentPos() {
        return (Document) this.currentPos;
    }

    public boolean equals(Object O){
        return O instanceof Driver && this.getId().equals(((Driver) O).getId());
    }

    public HashMap<Integer, ArrayList<RatingBoxUi>> getReviewsHash() {
        HashMap<Integer, ArrayList<RatingBoxUi>> driver_reviews = new HashMap<Integer, ArrayList<RatingBoxUi>>();

        for (ObjectId rating_id : this.getRatings()) {
            Document ratings_to_find = new Document("_id", rating_id);
            Document found = DbConnection.findOne(
                    "ratings",
                    ratings_to_find
            );
            Rating rating = new Rating(found);

            RatingBoxUi rating_ui =
                    new RatingBoxUi( //TODO
                            "",
                            "By " + rating.get_author().getName(),
                            "",
                            "",
                            rating.getRating(),
                            rating.getFeedback()
                    );


            if (driver_reviews.get(rating.getRating()) != null) {
                driver_reviews.get(rating.getRating()).add(rating_ui);
            } else {
                ArrayList<RatingBoxUi> new_cat = new ArrayList<RatingBoxUi>();
                new_cat.add(rating_ui);
                driver_reviews.put(rating.getRating(), new_cat);
            }
        }

        return driver_reviews;
    }

        public ArrayList<Rating> getRatingsBuilt() {
        ArrayList<Rating> ratings = new ArrayList<>();
        for (ObjectId rating_id : this.ratings) {
            ratings.add(new Rating(DbConnection.findOne("ratings", new Document("_id",rating_id))));
        }
        return ratings;
    }


    public Integer get_avg_rating() {
        int rating_total = 0;
        int rating_count = 0;
        for (Rating rating: this.getRatingsBuilt()) {
            rating_count++;
            rating_total+= rating.getRating();
        }
        return rating_count/rating_total;
    }


}
