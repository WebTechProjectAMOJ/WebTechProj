package controller.orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.user.Consumer;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.HashMap;


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
        order.setCustomer(user.getId());
        order.setRestaurant(restaurant.getId());
        order.setDelivery_address((Document) user.getAddress().get(addressKey));
        boolean written = order.write();
        user.addOrder(order.getId());
        if(written){
            session.setAttribute("message", "Successfully placed Order!");
            list.remove(restaurant);
            session.setAttribute("Basket", list);
            session.setAttribute("user", user);
        }
        response.sendRedirect(request.getContextPath() + "/" + session.getAttribute("accountType") + "-landing");
    }

    public void destroy() {
    }
}