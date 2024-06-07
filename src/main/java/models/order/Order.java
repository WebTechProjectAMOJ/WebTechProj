package models.order;

import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import models.ui_util.ItemBoxUi;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import models.user.Driver;

import javax.print.Doc;
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
    private Object delivery_address;
    @BsonProperty(value = "payment")
    private Document payment;
    @BsonProperty(value = "items")
    private ArrayList<ObjectId> items;
    @BsonProperty(value = "order_items")
    private ArrayList<OrderItems> order_items;
    @BsonProperty(value = "restaurant")
    private ObjectId restaurant;
    @BsonProperty(value = "consumer")
    private ObjectId consumer;

    public Order() {
    }

    public Order(ObjectId consumer, ObjectId restaurant) {
        this.consumer = consumer;
        this.restaurant = restaurant;
        this.order_items = new ArrayList<OrderItems>();
    }

    public Order(Document orderFound) {
        this.id = orderFound.getObjectId("_id");
        this.total = orderFound.getDouble("total");
        this.status = orderFound.getString("status");
        Document to_find = new Document("_id", orderFound.getObjectId("driver"));
        Document found = DbConnection.findOne(
                "drivers",
                to_find
        );
        this.driver = new Driver(found);
        ;
        this.delivery_address = (Document) orderFound.get("delivery_address");
        this.payment = (Document) orderFound.get("payment");
        this.items = (ArrayList<ObjectId>) orderFound.get("items");
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public double getTotal() {
        double price = 0;
        for(OrderItems i : this.order_items){
            price += i.getPrice() * i.getQuantity();
        }
        setTotal(price);
        return price;
    }

    public Document toDocument(){
        Document doc = new Document();
        doc.put("total", this.getTotal());
        doc.put("status", this.getStatus());
        if(this.driver != null){
            doc.put("driver", getDriver().getId());
        }
        doc.put("delivery_address", this.getDelivery_address());
        doc.put("payment", this.getPayment());
        ArrayList<Document> items = new ArrayList<>();
        for(OrderItems i : this.order_items){
            items.add(i.toDocument());
        }
        doc.put("items", items);
        return doc;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Object getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(Document delivery_address) {
        this.delivery_address = delivery_address;
    }

    public Document getPayment() {
        return payment;
    }

    public void setPayment(Document payment) {
        this.payment = payment;
    }

    public ArrayList<ObjectId> getItems() {
        return items;
    }

    public void setItems(ArrayList<ObjectId> items) {
        this.items = items;
    }


    public ItemBoxUi getUiItemBox() {
        //*Creates a ItemBox object to display in an item box element
        //*Creates the string from an address object
//        ArrayList<Object> addrs_doc = (ArrayList<Object>) this.delivery_address.get("address_components");

        StringBuilder addrs_str = new StringBuilder();
//        for (Object obj : addrs_doc) {
//            Document doc = (Document) obj;
//            addrs_str.append(doc.getString("short_name"));
//            addrs_str.append(", ");
//        }

        /*TODO:Change photo_url and action url*/
        return new ItemBoxUi(
//                addrs_str.toString(),
                "address",
                this.id.toString(),
                "",
                "");
    }

    public ArrayList<OrderItems> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(ArrayList<OrderItems> order_items) {
        this.order_items = order_items;
    }

    public void addOrder_item(OrderItems order_item) {
        this.order_items.add(order_item);
    }

    public ObjectId getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(ObjectId restaurant) {
        this.restaurant = restaurant;
    }

    public ObjectId getConsumer() {
        return consumer;
    }

    public void setConsumer(ObjectId consumer) {
        this.consumer = consumer;
    }

    @Override
    public String toString() {
        return getRestaurant().toString() + ", " + getConsumer().toString();
    }

    public boolean write(){
        Document doc = this.toDocument();
        InsertOneResult result =  DbConnection.insertOne("orders", doc);
        return result.wasAcknowledged();
    }
}
