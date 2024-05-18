package models.user;

import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import models.order.Order;
import models.ratings.Rating;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Restaurant extends User implements login{
    @BsonProperty(value = "food_items")
    private ArrayList<ObjectId> foodItems;
    @BsonProperty(value = "ratings")
    private ArrayList<ObjectId> ratings;
    @BsonProperty(value = "address")
    private Object address;
    @BsonProperty(value = "delivery_services")
    private ArrayList<ObjectId> deliveryServices;
    @BsonProperty(value = "tags")
    private ArrayList<ObjectId> tags;
    @BsonProperty(value = "combos")
    private ArrayList<ObjectId> combos;

    public Restaurant() {
        super();
    }

    public Restaurant(Document document) {
        super(document);
        setAddress(document.get("address"));
        setTags((ArrayList<ObjectId>) document.get("tags"));
        setDeliveryServices((ArrayList<ObjectId>) document.get("delivery_services"));
        setFoodItems((ArrayList<ObjectId>) document.get("food_items"));
        setRatings((ArrayList<ObjectId>) document.get("ratings"));
        setCombos((ArrayList<ObjectId>) document.get("combos"));
    }

    public Restaurant(String username,
                      String password,
                      String name,
                      String email,
                      ArrayList<ObjectId> orders,
                      ArrayList<ObjectId> foodItems,
                      ArrayList<ObjectId> combos,
                      ArrayList<ObjectId> ratings,
                      ArrayList<ObjectId> deliveryServices,
                      ArrayList<ObjectId> tags,
                      JsonObject address){
        super(username, password, name, email, orders);
        setFoodItems(foodItems);
        setRatings(ratings);
        setDeliveryServices(deliveryServices);
        setTags(tags);
        setAddress(address);
        setCombos(combos);
    }

    public boolean write(){
        Document doc = this.toDocument();
        doc.put("address", getAddress());
        doc.put("food_items", getFoodItems());
        doc.put("ratings", getRatings());
        doc.put("delivery_services", getDeliveryServices());
        doc.put("tags", getTags());
        doc.put("combos", getCombos());
        InsertOneResult written = DbConnection.insertOne("restaurants",doc);
        BsonObjectId id = (BsonObjectId) written.getInsertedId();
        this.setId(new ObjectId(String.valueOf(id.getValue())));
        return written.wasAcknowledged();
    }

    public ArrayList<ObjectId> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<ObjectId> foodItems) {
        this.foodItems = foodItems;
    }

    public ArrayList<ObjectId> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<ObjectId> ratings) {
        this.ratings = ratings;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public ArrayList<ObjectId> getDeliveryServices() {
        return deliveryServices;
    }

    public void setDeliveryServices(ArrayList<ObjectId> deliveryServices) {
        this.deliveryServices = deliveryServices;
    }

    public ArrayList<ObjectId> getTags() {
        return tags;
    }

    public void setTags(ArrayList<ObjectId> tags) {
        this.tags = tags;
    }

    public ArrayList<ObjectId> getCombos() {
        return combos;
    }

    public void setCombos(ArrayList<ObjectId> combos) {
        this.combos = combos;
    }

    public JsonObject getJsonAddress() {
        org.bson.Document doc = (Document) this.address;
        return new JsonObject(doc.toJson());
    }

    public ArrayList<Order> getListOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();
        for (ObjectId order: this.getOrders()) {
            Document order_to_find = new Document("_id", order);
            Document order_found = DbConnection.findOne(
                    "orders",
                    order_to_find
            );
            orders.add(new Order(order_found));
        }
        return orders;
    }
}
