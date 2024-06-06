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


@WebServlet(name = "Consumer Ongoing", value = "/customer-orders-ongoing")
public class consumerOngoing extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Check if login
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("accountType") != "customer") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        // Create the tags to display

        Document to_find = new Document("status", "complete");
        ArrayList<Document> found = DbConnection.find_with_not_filter(
                "orders",
                to_find
        );

        HashMap<String, ArrayList<ItemBoxUi>> orders_scroll = new HashMap<String, ArrayList<ItemBoxUi>>();

        // Create the Orders collection

        for (Document order_doc : found) {
            Order order = new Order(order_doc);

            if (orders_scroll.get(order.getStatus()) != null) {
                orders_scroll.get(order.getStatus()).add(order.getUiItemBox());
            } else {
                ArrayList<ItemBoxUi> new_cat = new ArrayList<ItemBoxUi>();
                new_cat.add(order.getUiItemBox());
                orders_scroll.put(order.getStatus(), new_cat);
            }
        }

        // Sets attributes for the view
        req.setAttribute("orders_to_scroll", orders_scroll);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/consumer_ongoing.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}