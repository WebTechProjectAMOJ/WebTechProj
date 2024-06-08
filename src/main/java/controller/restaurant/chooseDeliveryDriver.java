package controller.restaurant;

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


@WebServlet(name = "Choose Delivery Driver - Restaurants", value = "/choose-delivery-driver")
public class chooseDeliveryDriver extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        String order_id = req.getParameter("order_id");
        ObjectId orderId = new ObjectId(order_id);
        if (session == null ||  session.getAttribute("accountType") != "restaurant") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        Order order = new Order(DbConnection.findOne("orders", new Document("_id", orderId)));
        Restaurant resto = (Restaurant) session.getAttribute("user");
        ArrayList<ObjectId> services = resto.getDeliveryServices();
        ArrayList<DeliveryService> deliveryServices = new ArrayList<>();
        for (ObjectId id : services) {
            Document serviceDoc = DbConnection.findOne("delivery_services", new Document("_id", id));
            DeliveryService service = new DeliveryService(serviceDoc);
            deliveryServices.add(service);
        }
        ArrayList<Driver> drivers = new ArrayList<>();
        for (DeliveryService service : deliveryServices) {
            for(ObjectId id : service.getDrivers()) {
                Document driverDoc = DbConnection.findOne("drivers", new Document("_id", id));
                Driver driver = new Driver(driverDoc);
                drivers.add(driver);
            }
        }
        req.setAttribute("drivers", drivers);
        req.setAttribute("orderDetails", order);
        JsonObject address = new JsonObject(((Document) resto.getAddress()).toJson());
        req.setAttribute("currentAddress", address);
        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_choose_delivery_driver.jsp");
        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}