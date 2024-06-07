package controller.orders;

import dbconnection.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.order.OrderItems;
import models.user.Consumer;
import models.user.Restaurant;
import models.user.login;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Add order to basket", value = "/add-to-basket")
public class addToBasket extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        HashMap<Restaurant, Order> orders = (HashMap<Restaurant, Order>) session.getAttribute("Basket");
        String idHash = request.getParameter("idHash");
        ObjectId id = new ObjectId(idHash);
        String quantity = request.getParameter("quantity");
        int quantityInt = Integer.parseInt(quantity);
        String forwardto = request.getParameter("forwardto");
        String customization = request.getParameter("custom");
        String restaurantId = request.getParameter("restaurantId");
        System.out.println(id);
        ObjectId restaurantIdObj = new ObjectId(restaurantId);
        Restaurant restaurant = new Restaurant(restaurantIdObj);
        OrderItems newOrder = new OrderItems(id, quantityInt, customization);
        Consumer user = (Consumer) session.getAttribute("user");
        Order order = null;
        if(orders.containsKey(restaurant)) {
            System.out.println("Restaurant already exists");
            order = orders.get(restaurant);
        }
        else{
            order = new Order(user.getId(), restaurantIdObj);
        }
        order.addOrder_item(newOrder);
        orders.put(restaurant, order);
        session.setAttribute("Basket", orders);
        response.sendRedirect(request.getContextPath() + "/restaurant?id=" + forwardto);
    }

    public void destroy() {
    }
}