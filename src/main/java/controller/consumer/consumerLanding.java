package controller.consumer;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.tags.Tag;
import models.ui_util.ItemBoxUi;
import models.user.Consumer;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;


@WebServlet(name = "Consumer Landing", value = "/customer-landing")
public class consumerLanding extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Check if login
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("accountType") != "customer") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        // Create the tags to display

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

        // Create the recent Orders collection

        to_find = new Document("status", "complete");
        found = DbConnection.find(
                "orders",
                to_find
        );

        HashMap<String, ArrayList<ItemBoxUi>> orders_scroll = new HashMap<String, ArrayList<ItemBoxUi>>();

        for (Document order_doc : found) {
            Order order = new Order(order_doc);

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


        // Sets attributes for the view
        req.setAttribute("orders_to_scroll", orders_scroll);
        req.setAttribute("tags_to_scroll", resto_hash);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/consumer_dashboard.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}
