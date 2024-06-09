package controller.restaurant;

import controller.util.session_tools;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ui_util.ItemBoxUi;
import models.user.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "Restaurants History", value = "/restaurant-orders-history")
public class restaurantHistory extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("accountType") != "restaurant") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        session_tools.rebuild_user_session(session);
        Restaurant resto = (Restaurant) session.getAttribute("user");

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<String, ArrayList<ItemBoxUi>> order_hash = resto.get_complete_orders();


        // Sets attributes for the view
        req.setAttribute("user", resto);
        req.setAttribute("orders_to_scroll", order_hash);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_orders_history.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}