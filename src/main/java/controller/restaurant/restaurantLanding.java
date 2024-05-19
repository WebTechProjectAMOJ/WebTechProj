package controller.restaurant;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ui_util.ItemBoxUi;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Restaurants Landing", value = "/restaurant-landing")
public class restaurantLanding extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Gets the restaurant from the db
        // TODO: Change with retrieving from session
//        Document to_find = new Document("_id", new ObjectId("6645179c3418530bd4e7e158"));
//        Document found = DbConnection.findOne(
//                "restaurants",
//                to_find
//        );
        HttpSession session = req.getSession(false);
        Restaurant resto = (Restaurant) session.getAttribute("user");

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<String, ArrayList<ItemBoxUi>> order_hash = resto.getUIHashOrderStatus();


        // Sets attributes for the view
        req.setAttribute("user", resto);
        req.setAttribute("items_to_scroll", order_hash);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_orders_pending.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}