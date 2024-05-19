package models.items;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Item {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "price")
    private Double price;
    @BsonProperty(value = "tags")
    private ArrayList<ObjectId> tags;
    @BsonProperty(value = "tools_req")
    private ArrayList<ObjectId> toolsReq;
    @BsonProperty(value = "photo_url")
    private String photoUrl;
    @BsonProperty(value = "ratings")
    private ArrayList<ObjectId> ratings;
}
