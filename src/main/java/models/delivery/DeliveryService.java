package models.delivery;

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
}
