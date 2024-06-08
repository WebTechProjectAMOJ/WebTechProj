package controller.orders;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.user.Consumer;
import models.user.Driver;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.HashMap;


@WebServlet(name = "Deliver the order page", value = "/deliver-order")
public class deliverOrder extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("accountType") != "driver") {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        String order_id = request.getParameter("id");
        ObjectId order_id_obj = new ObjectId(order_id);
        Order order = new Order(DbConnection.findOne("orders", new Document("_id", order_id_obj)));
        Document consumerAddress = new Document();
        Document orderAddress = order.getDelivery_address_document();
        orderAddress = orderAddress.get("geometry", Document.class).get("location", Document.class);
        double lat1 = orderAddress.getDouble("lat");
        double lng1 = orderAddress.getDouble("lng");
        ObjectId restaurant_id = order.getRestaurant();
        Document restaurant_doc = DbConnection.findOne("restaurants", new Document("_id", restaurant_id));
        Document restaurant_address = (Document) restaurant_doc.get("address");
        restaurant_address = restaurant_address.get("geometry", Document.class).get("location", Document.class);
        double lat2 = restaurant_address.getDouble("lat");
        double lng2 = restaurant_address.getDouble("lng");
        Driver d = (Driver) session.getAttribute("user");
        Document driverPos = d.getDocumentCurrentPos();
        double curlat = driverPos.getDouble("latitude");
        double curlong = driverPos.getDouble("longitude");
        request.setAttribute("order_id", order_id);
        request.setAttribute("home_lat", lat1);
        request.setAttribute("home_lng", lng1);
        request.setAttribute("restaurant_lat", lat2);
        request.setAttribute("restaurant_lng", lng2);
        request.setAttribute("curr_lat", curlat);
        request.setAttribute("curr_lng", curlong);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/homepages/view_map_delivery.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}