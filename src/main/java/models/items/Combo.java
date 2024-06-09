package models.items;

import dbconnection.DbConnection;
import models.foodItems.Fooditem;
import models.ratings.Rating;
import models.ui_util.ItemBoxUi;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Combo {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "price")
    private Double price;
    @BsonProperty(value = "tags")
    private ArrayList<ObjectId> tags;
    @BsonProperty(value = "food_items")
    private ArrayList<ObjectId> foodItems;

    public Combo(Document document) {
        setName(document.getString("name"));
        setPrice(document.getDouble("price"));
        setId(document.getObjectId("_id"));
        setTags(document.get("tags", ArrayList.class));
        setFoodItems(document.get("food_items", ArrayList.class));
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ArrayList<ObjectId> getTags() {
        return tags;
    }

    public void setTags(ArrayList<ObjectId> tags) {
        this.tags = tags;
    }

    public ArrayList<ObjectId> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<ObjectId> foodItems) {
        this.foodItems = foodItems;
    }

    public ItemBoxUi getUiItemBox() {
        //TODO: Change action and photo_url
        return new ItemBoxUi(
                this.name,
                this.price.toString(),
                "",
                "",
                "combo"
        );
    }


    public ArrayList<Rating> getRatingsBuilt() {
        ArrayList<Rating> ratings = new ArrayList<>();

        for (ObjectId food_item_id : this.getFoodItems()) {
            Document to_find = new Document("_id", food_item_id);
            Document found = DbConnection.findOne(
                    "food_items",
                    to_find
            );
            Fooditem fooditem = new Fooditem(found);

            ratings.addAll(fooditem.getRatingsBuilt());
        }

        return ratings;
    }
}
