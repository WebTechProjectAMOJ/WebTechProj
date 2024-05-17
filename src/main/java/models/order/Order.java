package models.order;

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
    private Document delivery_address;
    @BsonProperty(value = "payment")
    private Document payment;
    @BsonProperty(value = "items")
    private ArrayList<ObjectId> items;

    public Order() {
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
        return total;
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

    public Document getDelivery_address() {
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
        ArrayList<Object> addrs_doc = (ArrayList<Object>) this.delivery_address.get("address_components");
        StringBuilder addrs_str = new StringBuilder();
        for (Object obj : addrs_doc) {
            Document doc = (Document) obj;
            addrs_str.append(doc.getString("short_name"));
            addrs_str.append(", ");
        }

        /*TODO:Change photo_url and action url*/
        return new ItemBoxUi(
                addrs_str.toString(),
                this.id.toString(),
                "",
                "");
    }

}
