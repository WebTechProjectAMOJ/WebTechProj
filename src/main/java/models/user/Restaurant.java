package models.user;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import models.items.Combo;
import models.ratings.Rating;
import models.ui_util.ItemBoxUi;
import models.foodItems.Fooditem;
import models.ui_util.RatingBoxUi;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant extends User implements login {
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

    private String icon_url = "https://uxwing.com/wp-content/themes/uxwing/download/location-travel-map/restaurant-icon.png";

    public Restaurant() {
        super();
    }

    public Restaurant(Document document) {
        super(document, "restaurant");
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
                      JsonObject address) {
        super(username, password, name, email, orders, "restaurant");
        setFoodItems(foodItems);
        setRatings(ratings);
        setDeliveryServices(deliveryServices);
        setTags(tags);
        setAddress(address);
        setCombos(combos);
    }

    public Restaurant(ObjectId id) {
        this(DbConnection.findOne("restaurants", new Document("_id", id)));
    }

    public boolean write() {
        Document doc = this.toDocument();
        doc.put("address", getAddress());
        doc.put("food_items", getFoodItems());
        doc.put("ratings", getRatings());
        doc.put("delivery_services", getDeliveryServices());
        doc.put("tags", getTags());
        doc.put("combos", getCombos());
        InsertOneResult written = DbConnection.insertOne("restaurants", doc);
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

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public boolean addFoodItem(Document foodItem) {
        ObjectId id = (ObjectId) foodItem.get("_id");
        Document find = new Document("_id", this.getId());
        BasicDBObject update = new BasicDBObject("food_items", id);
        BasicDBObject updatequery = new BasicDBObject("$push", update);
        this.foodItems.add(id);
        return DbConnection.updateOne("restaurants", find, updatequery).wasAcknowledged();
    }

    public JsonObject getJsonAddress() {
        org.bson.Document doc = (Document) this.address;
        return new JsonObject(doc.toJson());
    }

    public ItemBoxUi getUiItemBox() {
        //*Creates a ItemBox object to display in an item box element
        //*Creates the string from an address object
        Document addrs = (Document) this.address;
        ArrayList<Object> addrs_doc = (ArrayList<Object>) addrs.get("address_components");
        StringBuilder addrs_str = new StringBuilder();
        for (Object obj : addrs_doc) {
            Document doc = (Document) obj;
            addrs_str.append(doc.getString("short_name"));
            addrs_str.append(", ");
        }

        /*TODO:Change photo_url and action url*/
        return new ItemBoxUi(
                this.getName(),
                addrs_str.toString(),
                this.getIcon_url(),
                this.getId().toString());
    }

    public ArrayList<Fooditem> getFoodItemList() {
        ArrayList<Fooditem> soln = new ArrayList<Fooditem>();
        for (ObjectId i : this.foodItems) {
            System.out.println(i);
            Document d = DbConnection.findOne("food_items", new Document("_id", i));
            Fooditem fooditem = new Fooditem(d);
            soln.add(fooditem);
        }
        return soln;
    }

    public HashMap<String, ArrayList<ItemBoxUi>> get_food_items_ui() {
        HashMap<String, ArrayList<ItemBoxUi>> food_items = new HashMap<String, ArrayList<ItemBoxUi>>();

        for (ObjectId food_item_id : this.getFoodItems()) {
            Document food_item_to_find = new Document("_id", food_item_id);
            Document found = DbConnection.findOne(
                    "food_items",
                    food_item_to_find
            );
            Fooditem food_item = new Fooditem(found);

            if (food_items.get("Food Items") != null) {
                food_items.get("Food Items").add(food_item.getUiItemBox());
            } else {
                ArrayList<ItemBoxUi> new_cat = new ArrayList<ItemBoxUi>();
                new_cat.add(food_item.getUiItemBox());
                food_items.put("Food Items", new_cat);
            }
        }

        return food_items;

    }

    public HashMap<String, ArrayList<ItemBoxUi>> get_offers_ui() {
        HashMap<String, ArrayList<ItemBoxUi>> food_items = new HashMap<String, ArrayList<ItemBoxUi>>();

        for (ObjectId combo_id : this.getCombos()) {
            Document combos_to_find = new Document("_id", combo_id);
            Document found = DbConnection.findOne(
                    "combos",
                    combos_to_find
            );
            Combo combo = new Combo(found);

            if (food_items.get("Offers") != null) {
                food_items.get("Offers").add(combo.getUiItemBox());
            } else {
                ArrayList<ItemBoxUi> new_cat = new ArrayList<ItemBoxUi>();
                new_cat.add(combo.getUiItemBox());
                food_items.put("Offers", new_cat);
            }
        }

        return food_items;
    }

    public HashMap<String, ArrayList<RatingBoxUi>> get_food_item_reviews() {
        HashMap<String, ArrayList<RatingBoxUi>> foodItem_reviews = new HashMap<String, ArrayList<RatingBoxUi>>();

        for (ObjectId food_item_id : this.getFoodItems()) {
            Document food_item_to_find = new Document("_id", food_item_id);
            Document found = DbConnection.findOne(
                    "food_items",
                    food_item_to_find
            );
            Fooditem food_item = new Fooditem(found);

            ArrayList<Rating> ratings = food_item.getRatingsBuilt();
            ArrayList<RatingBoxUi> ratings_ui = new ArrayList<>();

            for (Rating rating : ratings) {
                ratings_ui.add(
                        new RatingBoxUi( //TODO
                                food_item.getName(),
                                "By " + rating.get_author().getName(),
                                "",
                                "",
                                rating.getRating(),
                                rating.getFeedback()
                        ));
            }


            if (foodItem_reviews.get(food_item.getName()) != null) {
                foodItem_reviews.get(food_item.getName()).addAll(ratings_ui);
            } else {
                ArrayList<RatingBoxUi> new_cat = new ArrayList<RatingBoxUi>(ratings_ui);
                foodItem_reviews.put(food_item.getName(), new_cat);
            }
        }

        return foodItem_reviews;
    }

    public HashMap<String, ArrayList<RatingBoxUi>> get_offer_reviews() {
        HashMap<String, ArrayList<RatingBoxUi>> offer_reviews = new HashMap<String, ArrayList<RatingBoxUi>>();

        for (ObjectId combo_id : this.getCombos()) {
            Document combo_to_find = new Document("_id", combo_id);
            Document found = DbConnection.findOne(
                    "combos",
                    combo_to_find
            );
            Combo combo = new Combo(found);

            ArrayList<Rating> ratings = combo.getRatingsBuilt();
            ArrayList<RatingBoxUi> ratings_ui = new ArrayList<>();

            for (Rating rating : ratings) {
                ratings_ui.add(
                        new RatingBoxUi( //TODO
                                combo.getName(),
                                "By " + rating.get_author().getName(),
                                "",
                                "",
                                rating.getRating(),
                                rating.getFeedback()
                        ));
            }


            if (offer_reviews.get(combo.getName()) != null) {
                offer_reviews.get(combo.getName()).addAll(ratings_ui);
            } else {
                ArrayList<RatingBoxUi> new_cat = new ArrayList<RatingBoxUi>(ratings_ui);
                offer_reviews.put(combo.getName(), new_cat);
            }
        }

        return offer_reviews;
    }

    public HashMap<Integer, ArrayList<RatingBoxUi>> get_resto_reviews() {
        HashMap<Integer, ArrayList<RatingBoxUi>> resto_reviews = new HashMap<Integer, ArrayList<RatingBoxUi>>();

        for (ObjectId rating_id : this.getRatings()) {
            Document ratings_to_find = new Document("_id", rating_id);
            Document found = DbConnection.findOne(
                    "ratings",
                    ratings_to_find
            );
            Rating rating = new Rating(found);

            RatingBoxUi rating_ui = //TODO
                    new RatingBoxUi(
                            "",
                            "By " + rating.get_author().getName(),
                            "",
                            "",
                            rating.getRating(),
                            rating.getFeedback()
                    );


            if (resto_reviews.get(rating.getRating()) != null) {
                resto_reviews.get(rating.getRating()).add(rating_ui);
            } else {
                ArrayList<RatingBoxUi> new_cat = new ArrayList<RatingBoxUi>();
                new_cat.add(rating_ui);
                resto_reviews.put(rating.getRating(), new_cat);
            }
        }

        return resto_reviews;
    }

    public ArrayList<Rating> getRatingsBuilt() {
        ArrayList<Rating> ratings = new ArrayList<>();
        for (ObjectId rating_id : this.ratings) {
            ratings.add(new Rating(DbConnection.findOne("ratings", new Document("_id", rating_id))));
        }
        return ratings;
    }


    public Integer get_avg_rating() {
        int rating_total = 0;
        int rating_count = 0;
        for (Rating rating : this.getRatingsBuilt()) {
            rating_count++;
            rating_total += rating.getRating();
        }
        return rating_count / rating_total;
    }

    @Override
    public boolean equals(Object O) {
        return O instanceof Restaurant && this.getId().equals(((Restaurant) O).getId());
    }

    public BasicDBObject toBasicDBObject() {
        BasicDBObject obj = super.toBasicDBObject();
        obj.append("tags", this.getTags());
        obj.append("delivery_services", this.getDeliveryServices());
        obj.append("address", this.getAddress());
        return obj;
    }

    public Restaurant update() {
        BasicDBObject set = new BasicDBObject();
        set.append("$set", this.toBasicDBObject());
        DbConnection.updateOne("restaurants", new Document("_id", this.getId()), set);
        return this;
    }
}
