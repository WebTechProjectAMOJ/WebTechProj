package models.foodItems;

import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import models.ratings.Rating;
import models.ui_util.ItemBoxUi;
import models.user.Restaurant;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Fooditem {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "price")
    private Double price;
    @BsonProperty(value = "tags")
    private ArrayList<ObjectId> tags;
    @BsonProperty(value = "tools_req")
    private ArrayList<ObjectId> tools_req;
    @BsonProperty(value = "photo_url")
    private String photo_url;
    @BsonProperty(value = "ratings")
    private ArrayList<ObjectId> ratings;

    public Fooditem(String name, Double price, ArrayList<ObjectId> tags, ArrayList<ObjectId> tools_req, String photo_url, ArrayList<ObjectId> ratings) {
        setName(name);
        setPrice(price);
        setTags(tags);
        setTools_req(tools_req);
        setPhoto_url(photo_url);
        setRatings(ratings);
    }

    public Fooditem(Document document) {
        setName(document.getString("name"));
        setPrice(document.getDouble("price"));
        setId(document.getObjectId("_id"));
        setTags(document.get("tags", ArrayList.class));
        setTools_req(document.get("tools_req", ArrayList.class));
        setPhoto_url(document.getString("photo_url"));
        setRatings(document.get("ratings", ArrayList.class));
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

    public ArrayList<ObjectId> getTags() {
        return tags;
    }

    public void setTags(ArrayList<ObjectId> tags) {
        this.tags = tags;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ArrayList<ObjectId> getTools_req() {
        return tools_req;
    }

    public void setTools_req(ArrayList<ObjectId> tools_req) {
        this.tools_req = tools_req;
    }

    public ArrayList<ObjectId> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<ObjectId> ratings) {
        this.ratings = ratings;
    }

    public String getPhoto_url() {
        String ph_url = this.photo_url;
        if (ph_url.length() < 2){
            ph_url = "https://cdn-icons-png.flaticon.com/512/2771/2771401.png";
        }
        return ph_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.put("name", getName());
        doc.put("price", getPrice());
        doc.put("tags", getTags());
        doc.put("tools_req", getTools_req());
        doc.put("photo_url", getPhoto_url());
        doc.put("ratings", getRatings());
        return doc;
    }

    public boolean write(Restaurant restaurant) {
        Document doc = this.toDocument();
        InsertOneResult written = DbConnection.insertOne("food_items", doc);
        BsonObjectId id = (BsonObjectId) written.getInsertedId();
        this.setId(new ObjectId(String.valueOf(id.getValue())));
        boolean res = restaurant.addFoodItem(doc);
        return written.wasAcknowledged() && res;
    }

    public ItemBoxUi getUiItemBox() {
        //*Creates a ItemBox object to display in an item box element
        //*Creates the string from an address object

        /*TODO:Change photo_url and action url*/
        return new ItemBoxUi(
                this.getName(),
                this.getPrice().toString(),
                getPhoto_url(),
                this.getId().toString());
    }

    public ArrayList<Rating> getRatingsBuilt() {

        ArrayList<Rating> ratings = new ArrayList<>();

        for (ObjectId rating_id : this.ratings) {
            Document rating_to_find = new Document("_id", rating_id);
            Document found = DbConnection.findOne(
                    "ratings",
                    rating_to_find
            );

            Rating rating = new Rating(found);

            ratings.add(rating);
        }

        return ratings;
    }
}
