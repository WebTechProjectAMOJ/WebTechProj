package models.user;

import dbconnection.DbConnection;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class User {
    public User(Document document){
        this.id = document.getObjectId("_id");
        this.name = document.getString("name");
        this.credentials = new Credential((Document) document.get("credentials"));
        setEmail(document.getString("email"));
        setOrders((ArrayList<ObjectId>) document.get("orders"));
    }

    public User(String username, String password, String name, String email, ArrayList<ObjectId> orders){
        this.credentials = new Credential(username, password);
        this.name = name;
        this.email = email;
        this.orders = orders;
    }

    @BsonProperty(value = "last_name")
    private String name;
    @BsonProperty(value = "credentials")
    private Credential credentials;
    @BsonProperty(value = "email")
    private String email;
    @BsonProperty(value = "orders")
    private ArrayList<ObjectId> orders;
    @BsonId
    private ObjectId id;

    public User() {}

    public Document toDocument(){
        Document document = new Document();
        document.put("name", name);
        document.put("credentials", credentials.toDocument());
        document.put("email", email);
        document.put("orders", orders);
        return document;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Credential getCredentials() {
        return credentials;
    }

    public void setCredentials(Credential credentials) {
        this.credentials = credentials;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ObjectId> getOrders() {
        return orders;
    }

    public ArrayList<ObjectId> addOrder(ObjectId order){
        this.orders.add(order);
        return orders;
    }

    public void setOrders(ArrayList<ObjectId> orders) {
        this.orders = orders;
    }
}
