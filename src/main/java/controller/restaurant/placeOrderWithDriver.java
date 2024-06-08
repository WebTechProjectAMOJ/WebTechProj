package controller.restaurant;

import com.mongodb.BasicDBObject;
import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.delivery.DeliveryService;
import models.order.Order;
import models.user.Driver;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Place Order With Driver", value = "/place-order-with-driver")
public class placeOrderWithDriver extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null ||  session.getAttribute("accountType") != "restaurant") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        String order_id = req.getParameter("order_id");
        // set status, add to restaurant, add to driver
        ObjectId orderId = new ObjectId(order_id);
        DbConnection.setOne("orders", new Document("_id", orderId), "status", "delivering");
        String driver_id = req.getParameter("driver_id");
        ObjectId driverIdObject = new ObjectId(driver_id);
        BasicDBObject updateDoc = new BasicDBObject("$push", new Document("orders", orderId));
        DbConnection.updateOne("drivers", new Document("_id", driverIdObject), updateDoc);
        session.setAttribute("message", "Order Successfully Placed With Driver");
        resp.sendRedirect(req.getContextPath() + "/" + "restaurant" + "-landing");

    }

    public void destroy() {
    }
}