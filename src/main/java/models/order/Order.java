package models.order;

import dbconnection.DbConnection;
import models.items.Item;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import models.user.Driver;

import java.util.ArrayList;

public class Order {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "total")
    private double total;
    @BsonProperty(value = "status")
    private String status;
    @BsonProperty(value = "driver")
    private Driver driver;
    @BsonProperty(value = "delivery_address")
    private Document delivery_address;
    @BsonProperty(value = "payment")
    private Document payment;
    @BsonProperty(value = "items")
    private ArrayList<ObjectId> items;

    public Order() {}

    public Order(Document orderFound) {
        this.id = orderFound.getObjectId("_id");
        this.total = orderFound.getDouble("total");
        this.status = orderFound.getString("status");
        Document to_find = new Document("_id", orderFound.getObjectId("driver"));
        Document found = DbConnection.findOne(
                "drivers",
                to_find
        );
        this.driver = new Driver(found);;
        this.delivery_address = (Document) orderFound.get("delivery_address");
        this.payment = (Document) orderFound.get("payment");
        this.items = (ArrayList<ObjectId>) orderFound.get("items");
    }

}
