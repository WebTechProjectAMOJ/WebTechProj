package models.user;

import dbconnection.DbConnection;
import dbconnection.Pash;
import models.order.Order;
import models.ui_util.ItemBoxUi;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public User(Document document, String accountType) {
        this.id = document.getObjectId("_id");
        this.name = document.getString("name");
        this.credentials = new Credential((Document) document.get("credentials"));
        setEmail(document.getString("email"));
        setOrders((ArrayList<ObjectId>) document.get("orders"));
        this.accountType = accountType;
    }

    public User(String username, String password, String name, String email, ArrayList<ObjectId> orders, String accountType) {
        this.credentials = new Credential(username, password);
        this.name = name;
        this.email = email;
        this.orders = orders;
        this.accountType = accountType;
    }

    @BsonProperty(value = "last_name")
    private String name;
    @BsonProperty(value = "credentials")
    private Credential credentials;
    @BsonProperty(value = "email")
    private String email;
    @BsonProperty(value = "orders")
    private ArrayList<ObjectId> orders;
    @BsonId
    private ObjectId id;
    public String accountType;

    public User() {
    }

    public Document toDocument() {
        Document document = new Document();
        document.put("name", name);
        document.put("credentials", credentials.toDocument());
        document.put("email", email);
        document.put("orders", orders);
        return document;
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

    public Credential getCredentials() {
        return credentials;
    }

    public void setCredentials(Credential credentials) {
        this.credentials = credentials;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ObjectId> getOrders() {
        return orders;
    }

    public ArrayList<ObjectId> addOrder(ObjectId order) {
        this.orders.add(order);
        return orders;
    }

    public void setOrders(ArrayList<ObjectId> orders) {
        this.orders = orders;
    }

    public boolean verify(String password) {
        return Pash.verify(this.credentials.getPassword(), password);
    }

    public ArrayList<Order> getListOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();
        for (ObjectId order : this.getOrders()) {
            Document order_to_find = new Document("_id", order);
            Document order_found = DbConnection.findOne(
                    "orders",
                    order_to_find
            );
            orders.add(new Order(order_found));
        }
        return orders;
    }

    public HashMap<String, ArrayList<ItemBoxUi>> getUIHashOrderStatus() {
        HashMap<String, ArrayList<ItemBoxUi>> order_hash = new HashMap<String, ArrayList<ItemBoxUi>>();
        for (Order ord : this.getListOrders()) {
            if (order_hash.get(ord.getStatus()) != null && this.getOrders().contains(ord.getId())) {
                order_hash.get(ord.getStatus()).add(ord.getUiItemBox());
            } else {
                ArrayList<ItemBoxUi> new_stat = new ArrayList<ItemBoxUi>();
                new_stat.add(ord.getUiItemBox());
                order_hash.put(ord.getStatus(), new_stat);
            }
        }
        return order_hash;
    }

    public String getAccountType() {
        return accountType;
    }


    public HashMap<String, ArrayList<ItemBoxUi>> get_not_complete_orders() {
        HashMap<String, ArrayList<ItemBoxUi>> orders_scroll = new HashMap<String, ArrayList<ItemBoxUi>>();

        Document to_find = new Document("status", "complete");
        ArrayList<Document> found = DbConnection.find_with_not_filter(
                "orders",
                to_find
        );


        for (Document order_doc : found) {
            Order order = new Order(order_doc);

            if (orders_scroll.get(order.getStatus()) != null && this.getOrders().contains(order.getId())) {
                orders_scroll.get(order.getStatus()).add(order.getUiItemBox());
            } else {
                ArrayList<ItemBoxUi> new_cat = new ArrayList<ItemBoxUi>();
                new_cat.add(order.getUiItemBox());
                orders_scroll.put(order.getStatus(), new_cat);
            }
        }

        return orders_scroll;
    }

    public HashMap<String, ArrayList<ItemBoxUi>> get_complete_orders() {
        HashMap<String, ArrayList<ItemBoxUi>> orders_scroll = new HashMap<String, ArrayList<ItemBoxUi>>();

        Document to_find = new Document("status", "complete");
        ArrayList<Document> found = DbConnection.find(
                "orders",
                to_find
        );

        for (Document order_doc : found) {
            Order order = new Order(order_doc);

            if (orders_scroll.get(order.getStatus()) != null && this.getOrders().contains(order.getId())) {
                orders_scroll.get(order.getStatus()).add(order.getUiItemBox());
            } else {
                ArrayList<ItemBoxUi> new_cat = new ArrayList<ItemBoxUi>();
                new_cat.add(order.getUiItemBox());
                orders_scroll.put(order.getStatus(), new_cat);
            }
        }

        return orders_scroll;
    }


}
