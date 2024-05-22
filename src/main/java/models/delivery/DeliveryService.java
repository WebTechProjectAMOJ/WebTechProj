package models.delivery;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import models.user.Driver;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class DeliveryService {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "drivers")
    private ArrayList<ObjectId> drivers;
    @BsonProperty(value = "fee")
    private double fee;

    public DeliveryService(String name) {
        this(DbConnection.findOne("delivery_services", new Document("name", name)));
    }

    public DeliveryService(Document document) {
        setId(document.getObjectId("_id"));
        setName(document.getString("name"));
        setFee(document.getDouble("fee"));
        setDrivers(document.get("drivers", ArrayList.class));
    }

    public DeliveryService(String name, double fee, ArrayList<ObjectId> drivers) {
        setName(name);
        setFee(fee);
        if(drivers == null)
            setDrivers(new ArrayList<ObjectId>());
        else setDrivers(drivers);
    }

    public boolean insertDriver(ObjectId driver) {
        BasicDBObject driverId = new BasicDBObject("drivers", driver);
        BasicDBObject updatequery = new BasicDBObject("$push", driverId);
        Document find = new Document("_id", this.getId());
        addDriver(driver);
        return DbConnection.updateOne("delivery_services", find, updatequery).wasAcknowledged();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public ArrayList<ObjectId> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<ObjectId> drivers) {
        this.drivers = drivers;
    }

    public void addDriver(ObjectId driver) {
        this.drivers.add(driver);
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.put("name", this.getName());
        doc.put("fee", this.getFee());
        doc.put("drivers", this.getDrivers());
        return doc;
    }

    public boolean write(){
        Document doc = this.toDocument();
        InsertOneResult written = DbConnection.insertOne("delivery_services",doc);
        BsonObjectId id = (BsonObjectId) written.getInsertedId();
        this.setId(new ObjectId(String.valueOf(id.getValue())));
        return written.wasAcknowledged();
    }
}
