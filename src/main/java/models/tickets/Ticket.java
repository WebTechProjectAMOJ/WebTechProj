package models.tickets;

import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Ticket {

    private ObjectId id;
    private String title;
    private String description;
    private ObjectId restaurantId;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ticket(String title, String description, ObjectId restaurantId) {
        this.title = title;
        this.description = description;
        this.restaurantId = restaurantId;
        this.status = "open";
    }

    public Ticket(Document document) {
        id = document.getObjectId("_id");
        title = document.getString("title");
        description = document.getString("description");
        restaurantId = document.getObjectId("restaurant_id");
        this.status = document.getString("status");
    }

    public Document toDocument() {
        Document document = new Document();
        document.put("title", title);
        document.put("description", description);
        document.put("restaurant_id", restaurantId);
        document.put("status", status);
        return document;
    }

    public boolean write(){
        Document document = toDocument();
        InsertOneResult insertOneResult = DbConnection.insertOne("tickets", document);
        BsonObjectId id = (BsonObjectId) insertOneResult.getInsertedId();
        this.setId(new ObjectId(String.valueOf(id.getValue())));
        return insertOneResult.wasAcknowledged();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(ObjectId restaurantId) {
        this.restaurantId = restaurantId;
    }
}
