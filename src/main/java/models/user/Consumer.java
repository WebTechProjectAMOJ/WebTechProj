package models.user;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.InsertOneResult;
import dbconnection.DbConnection;
import models.order.Order;
import models.tags.Tag;
import models.ui_util.ItemBoxUi;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;


public class Consumer extends User implements login {

    public Consumer(Document document) {
        super(document, "customer");
        setFirst_name(document.getString("first_name"));
        setAddress((ArrayList<Object>) document.get("address"));
    }

    public Consumer(String username, String password, String name, String email, Document preferences,ArrayList<ObjectId> orders, String first_name, ArrayList<Object> address){
        super(username, password, name, email, orders, "customer");
        setFirst_name(first_name);
        setAddress(address);
        setPreferences(preferences);
    }

    @BsonProperty(value = "first_name")
    private String first_name;
    @BsonProperty(value = "address")
    private ArrayList<Object> address;
    @BsonProperty(value = "preferences")
    private Document preferences;

    public Document getPreferences() {
        return preferences;
    }

    public void setPreferences(Document preferences) {
        this.preferences = preferences;
    }

    public ArrayList<ObjectId> getLikes() {
        return (ArrayList<ObjectId>) this.preferences.get("likes");
    }

    public ArrayList<ObjectId> getDislikes() {
        return (ArrayList<ObjectId>) this.preferences.get("dislikes");
    }

    public ArrayList<ObjectId> getAllergens() {
        return (ArrayList<ObjectId>) this.preferences.get("allergens");
    }

    public void setLikes(ArrayList<ObjectId> likes) {
        this.preferences.put("likes", likes);
    }

    public void setDislikes(ArrayList<ObjectId> dislikes) {
        this.preferences.put("dislikes", dislikes);
    }

    public void setAllergens(ArrayList<ObjectId> allergens) {
        this.preferences.put("allergens", allergens);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public ArrayList<Object> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<Object> address) {
        this.address = address;
    }

    public boolean write(){
        Document doc = this.toDocument();
        doc.put("first_name", getFirst_name());
        doc.put("address", getAddress());
        doc.put("preferences", getPreferences());
        InsertOneResult written = DbConnection.insertOne("consumers",doc);
        BsonObjectId id = (BsonObjectId) written.getInsertedId();
        this.setId(new ObjectId(String.valueOf(id.getValue())));
        return written.wasAcknowledged();
    }

    public Consumer update() {
        BasicDBObject set = new BasicDBObject();
        set.append("$set", this.toBasicDBObject());
        DbConnection.updateOne("consumers", new Document("_id", this.getId()),set);
        return this;
    }

    public boolean equals(Object O){
        return O instanceof Consumer && this.getId().equals(((Consumer) O).getId());
    }

    public HashMap<String, ArrayList<ItemBoxUi>> get_restaurant_options() {

        Document to_find = new Document("type", "preference");
        ArrayList<Document> found = DbConnection.find(
                "tags",
                to_find
        );

        HashMap<String, ArrayList<ItemBoxUi>> resto_hash = new HashMap<String, ArrayList<ItemBoxUi>>();
        for (Document tag_doc : found) {
            Tag tag = new Tag(tag_doc);

            Document resto_find = new Document("tags", tag.getId());
            ArrayList<Document> found_resto_docs = DbConnection.find(
                    "restaurants",
                    resto_find
            );

            ArrayList<ItemBoxUi> restos_filtered = new ArrayList<ItemBoxUi>();
            for (Document rest_doc : found_resto_docs) {
                Restaurant resto = new Restaurant(rest_doc);
                restos_filtered.add(resto.getUiItemBox());
            }

            if (resto_hash.get(tag.getName()) != null) {
                resto_hash.get(tag.getName()).addAll(restos_filtered);
            } else {
                ArrayList<ItemBoxUi> new_cat = new ArrayList<ItemBoxUi>(restos_filtered);
                resto_hash.put(tag.getName(), new_cat);
            }
        }

        ArrayList<String> empty_tags = new ArrayList<>();

        for (String tag_name : resto_hash.keySet()) {
            if (resto_hash.get(tag_name).isEmpty()) {
                empty_tags.add(tag_name);
            }
        }

        for (String tag_name : empty_tags) {
            resto_hash.remove(tag_name);
        }

        return resto_hash;
    }

    public HashMap<String, ArrayList<ItemBoxUi>> get_recent_orders() {

        Document to_find = new Document("status", "complete");
        ArrayList<Document> found = DbConnection.find(
                "orders",
                to_find
        );

        HashMap<String, ArrayList<ItemBoxUi>> orders_scroll = new HashMap<String, ArrayList<ItemBoxUi>>();

        for (Document order_doc : found) {
            Order order = new Order(order_doc);

            if (this.getOrders().contains(order.getId())) {
                if (Objects.equals(order.getStatus(), "complete")) {
                    if (orders_scroll.get("Recent Orders") != null) {
                        orders_scroll.get("Recent Orders").add(order.getUiItemBox());
                        Collections.reverse(orders_scroll.get("Recent Orders"));
                    } else {
                        ArrayList<ItemBoxUi> new_cat = new ArrayList<ItemBoxUi>();
                        new_cat.add(order.getUiItemBox());
                        orders_scroll.put("Recent Orders", new_cat);
                    }
                }
            }
        }

        return orders_scroll;
    }

}
