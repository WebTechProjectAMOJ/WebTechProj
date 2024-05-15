package controller.restaurant;

import dbconnection.DbConnection;
import dbconnection.Pash;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.user.Driver;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Restaurants Landing", value = "/restaurant-landing")
public class restaurantLanding extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");

        Document to_find = new Document("_id", new ObjectId("6644be0705f3893e58e45614"));

        Document found = DbConnection.findOne(
                "restaurants",
                to_find
        );

        Restaurant resto = new Restaurant(found);

        req.setAttribute("user", resto);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_orders_pending.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}