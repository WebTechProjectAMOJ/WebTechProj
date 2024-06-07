package controller.restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.ui_util.ItemBoxUi;
import models.user.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Restaurants Pending Accepts", value = "/restaurant-orders-to-accept")
public class restaurantToAccept extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null ||  session.getAttribute("accountType") != "restaurant") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        Restaurant resto = (Restaurant) session.getAttribute("user");

        // Makes a hashmap with list of all status and a list of all orders
        ArrayList<Order> orders = resto.getOrdersNotAccepted();
        System.out.println(orders.size());

        // Sets attributes for the view
        req.setAttribute("user", resto);
        req.setAttribute("order_list", orders);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_orders_to_accept.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}