package models.user;

import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import models.order.Order;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;


public class Consumer extends User implements login {

    public Consumer(Document document){
        super(document, "customer");
        setFirst_name(document.getString("first_name"));
        setAddress((ArrayList<JsonObject>) document.get("address"));
    }

    public Consumer(String username, String password, String name, String email, Document preferences,ArrayList<ObjectId> orders, String first_name, ArrayList<JsonObject> address){
        super(username, password, name, email, orders, "customer");
        setFirst_name(first_name);
        setAddress(address);
        setPreferences(preferences);
    }

    @BsonProperty(value = "first_name")
    private String first_name;
    @BsonProperty(value = "address")
    private ArrayList<JsonObject> address;
    @BsonProperty(value = "preferences")
    private Document preferences;

    public Document getPreferences(){
        return preferences;
    }

    public void setPreferences(Document preferences){
        this.preferences = preferences;
    }

    public ArrayList<ObjectId> getLikes(){
        return (ArrayList<ObjectId>) this.preferences.get("likes");
    }

    public ArrayList<ObjectId> getDislikes(){
        return (ArrayList<ObjectId>) this.preferences.get("dislikes");
    }

    public ArrayList<ObjectId> getAllergens(){
        return (ArrayList<ObjectId>) this.preferences.get("allergens");
    }

    public void setLikes(ArrayList<ObjectId> likes){
        this.preferences.put("likes", likes);
    }

    public void setDislikes(ArrayList<ObjectId> dislikes){
        this.preferences.put("dislikes", dislikes);
    }

    public void setAllergens(ArrayList<ObjectId> allergens){
        this.preferences.put("allergens", allergens);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public ArrayList<JsonObject> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<JsonObject> address) {
        this.address = address;
    }

    public boolean write(){
        Document doc = this.toDocument();
        doc.put("first_name", getFirst_name());
        doc.put("address", getAddress());
        doc.put("preferences", getPreferences());
        InsertOneResult written = DbConnection.insertOne("consumers",doc);
        BsonObjectId id = (BsonObjectId) written.getInsertedId();
        this.setId(new ObjectId(String.valueOf(id.getValue())));
        return written.wasAcknowledged();
    }
}
