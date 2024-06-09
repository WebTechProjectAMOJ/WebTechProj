package models.order;

import dbconnection.DbConnection;
import models.foodItems.Fooditem;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class OrderItems {

    public OrderItems(ObjectId id, int quantity, String customizations) {
        this.fooditem = new Fooditem(DbConnection.findOne("food_items", new Document("_id", id)));
        this.quantity = quantity;
        this.customizations = customizations;
        this.id = new ObjectId();
    }

    public OrderItems(Document document) {
        ObjectId id = document.getObjectId("fooditem");
        this.fooditem = new Fooditem(DbConnection.findOne("food_items", new Document("_id", id)));
        this.quantity = document.getInteger("quantity");
        this.customizations = document.getString("customizations");
        this.id = new ObjectId();
    }

    @BsonId
    private ObjectId id;

    @BsonProperty(value = "fooditem")
    private Fooditem fooditem;

    @BsonProperty(value = "quantity")
    private int quantity;

    @BsonProperty(value = "customizations")
    private String customizations;

    public String type;

    public double getPrice(){
        return fooditem.getPrice() * quantity;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Fooditem getFooditem() {
        return fooditem;
    }

    public void setFooditem(Fooditem fooditem) {
        this.fooditem = fooditem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomizations() {
        return customizations;
    }

    public void setCustomizations(String customizations) {
        this.customizations = customizations;
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.put("fooditem", fooditem.getId());
        doc.put("quantity", quantity);
        doc.put("customizations", customizations);
        return doc;
    }
}
