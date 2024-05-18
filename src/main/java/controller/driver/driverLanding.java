package controller.driver;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ui_util.ItemBoxUi;
import models.user.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Driver Landing", value = "/driver-landing")
public class driverLanding extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Gets the restaurant from the db
        // TODO: Change with retrieving from session
        Document to_find = new Document("_id", new ObjectId("6645179c00000000008daa2e"));
        Document found = DbConnection.findOne(
                "drivers",
                to_find
        );
        Driver driver = new Driver(found);

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<String, ArrayList<ItemBoxUi>> order_hash = driver.getUIHashStatus();

        // Sets attributes for the view
         req.setAttribute("user", driver);
        req.setAttribute("items_to_scroll", order_hash);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/driver_orders_pending.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}
