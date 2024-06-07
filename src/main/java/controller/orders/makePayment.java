package controller.orders;

import com.mongodb.BasicDBObject;
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
import models.user.Consumer;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Make Payment", value = "/make_payment")
public class makePayment extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        HashMap<Restaurant, Order> list = (HashMap<Restaurant, Order>) session.getAttribute("Basket");
        String restaurantKeyString = request.getParameter("restaurant-key");
        String addressKeyString = request.getParameter("address-key");
        int addressKey = Integer.parseInt(addressKeyString);
        Restaurant restaurant = null;
        ObjectId restaurantId = new ObjectId(restaurantKeyString);
        restaurant = new Restaurant(restaurantId);
        Order order = list.get(restaurant);
        Consumer user = (Consumer) session.getAttribute("user");
        order.setStatus("Placed");
        order.setPayment(new Document("Payment", "Completed"));
        order.setConsumer(user.getId());
        order.setRestaurant(restaurant.getId());
        order.setDelivery_address((Document) user.getAddress().get(addressKey));
        System.out.println(user.getAddress().get(addressKey));
        boolean written = order.write();
        if(written){
            session.setAttribute("message", "Successfully added food item!");
        }
        response.sendRedirect(request.getContextPath() + "/" + session.getAttribute("accountType") + "-landing");
    }

    public void destroy() {
    }
}