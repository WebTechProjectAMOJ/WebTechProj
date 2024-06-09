package controller.consumer;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.foodItems.Fooditem;
import models.order.Order;
import models.order.OrderItems;
import models.ratings.Rating;
import models.user.Consumer;
import models.user.Driver;
import models.user.Restaurant;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;


@WebServlet(name = "Consumer Review", value = "/customer-review")
public class consumerReview extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Check if login
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("accountType") != "customer") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        Consumer user = (Consumer) session.getAttribute("user");
        Order order = new Order(DbConnection.findOne("orders", new Document("_id", new ObjectId(req.getParameter("id")))));
        ObjectId restaurant_id = order.getRestaurant();
        Driver driver = new Driver(
                DbConnection.findOne("drivers", new Document("orders", order.getId()))
        );


        ArrayList<Fooditem> fooditems = new ArrayList<>();
        for (OrderItems item : order.getOrder_items()) {
            fooditems.add(item.getFooditem());
        }

        // Sets attributes for the view
        req.setAttribute("order", order);
        req.setAttribute("driver_id", driver.getId());
        req.setAttribute("restaurant_id", restaurant_id);
        req.setAttribute("food_items", fooditems);


        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/consumer_review.jsp");

        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("accountType") != "customer") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        Consumer user = (Consumer) session.getAttribute("user");


        if (Objects.equals(req.getParameter("action"), "submit-review")) {
            //Place reviews

            for (Iterator<String> it = req.getParameterNames().asIterator(); it.hasNext(); ) {
                String names = it.next();

                String[] input = names.split("-");

                if (input[0].equals("item")) {

                }
            }


            // Place Restaurant Rating

            Restaurant resto = new Restaurant(DbConnection.findOne("restaurants", new Document("_id", new ObjectId(req.getParameter("restaurant_id")))));
            Document resto_rating = new Document();
            resto_rating.put("consumer_id", user.getId());
            resto_rating.put("feedback", req.getParameter("restaurant-feedback-" + resto.getId()));
            resto_rating.put("value", Integer.parseInt(req.getParameter("restaurant-rating-" + resto.getId())));

            ObjectId rating_id = DbConnection.insertOne("ratings", resto_rating).getInsertedId().asObjectId().getValue();

            ArrayList<ObjectId> rating_ids = resto.getRatings();
            rating_ids.add(rating_id);
            resto.setRatings(rating_ids);
            resto.update();

            // Place Driver Rating


            resp.sendRedirect(req.getContextPath() + "/customer-history");
        } else {
            resp.sendRedirect(req.getContextPath() + "/customer-review?id=" + req.getParameter("id"));
        }


    }


    public void destroy() {
    }
}