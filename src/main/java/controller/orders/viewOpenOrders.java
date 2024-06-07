package controller.orders;

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
import models.ui_util.ItemBoxUi;
import models.user.Restaurant;
import org.bson.Document;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Get Open Orders - Restaurant", value = "/open-orders")
public class viewOpenOrders extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Restaurant restaurant = (Restaurant) session.getAttribute("user");
        Document searchTerm = new Document();
        searchTerm.put("restaurant", restaurant.getId());
        searchTerm.put("status", "accepted");
        ArrayList<Document> orderList = DbConnection.find("orders", searchTerm);
        ArrayList<Order> orders = new ArrayList<>();
        for (Document order : orderList) {
            orders.add(new Order(order));
        }
        request.setAttribute("orderList", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/homepages/restaurant_orders_open.jsp");
        dispatcher.forward(request, response);
    }

}