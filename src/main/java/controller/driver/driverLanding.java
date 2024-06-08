package controller.driver;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.ui_util.ItemBoxUi;
import models.user.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


@WebServlet(name = "Driver Landing", urlPatterns = {"/driver-landing", "/driver-pending", "/driver-history"})
public class driverLanding extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("accountType") != "driver") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        String url = req.getRequestURI().split("/")[req.getRequestURI().split("/").length - 1];

        Driver driver = (Driver) session.getAttribute("user");

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<String, ArrayList<ItemBoxUi>> order_hash = null;

        if (url.equals("driver-landing")) {
            ArrayList<Order> orders = new ArrayList<>();
            Document driverDoc = DbConnection.findOne("drivers", new Document("_id", driver.getId()));
            driver = new Driver(driverDoc);
            ArrayList<ObjectId> order_ids = driver.getOrders();
            for (ObjectId order_id : order_ids) {
                Document orderDoc = DbConnection.findOne("orders", new Document("_id", order_id));
                Order order = new Order(orderDoc);
                if (!Objects.equals(order.getStatus(), "done")){
                    orders.add(order);
                }
            }
            session.setAttribute("user", driver);
            req.setAttribute("orders", orders);
            RequestDispatcher dispatcher = req
                    .getRequestDispatcher("/views/homepages/driver_orders_to_deliver.jsp");
            dispatcher.forward(req, resp);
        }
        else {
            if (url.equals("driver-pending")) {
                order_hash = driver.get_not_complete_orders();
            }

            if (url.equals("driver-history")) {
                order_hash = driver.get_complete_orders();
            }

            // Sets attributes for the view
            req.setAttribute("user", driver);
            req.setAttribute("orders_to_scroll", order_hash);

            RequestDispatcher dispatcher = req
                    .getRequestDispatcher("/views/homepages/driver_orders_pending.jsp");

            dispatcher.forward(req, resp);
        }

    }

    public void destroy() {
    }
}
