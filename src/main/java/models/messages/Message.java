package models.messages;

import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Message {
    private ObjectId id;
    private String message;
    private String sender;
    private ObjectId orderid;

    public Message(String message, String sender, ObjectId orderid) {
        this.message = message;
        this.sender = sender;
        this.orderid = orderid;
    }

    public Message(Document document) {
        this.id = document.getObjectId("_id");
        this.message = document.getString("message");
        this.sender = document.getString("sender");
        this.orderid = document.getObjectId("orderid");
    }

    public Document toDocument() {
        Document document = new Document();
        document.put("message", this.message);
        document.put("sender", this.sender);
        document.put("orderid", this.orderid);
        return document;
    }

    public boolean write(){
        Document doc = toDocument();
        InsertOneResult insertOneResult = DbConnection.insertOne("messages", doc);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ObjectId getOrderid() {
        return orderid;
    }

    public void setOrderid(ObjectId orderid) {
        this.orderid = orderid;
    }
}
