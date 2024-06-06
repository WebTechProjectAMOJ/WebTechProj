package controller.restaurant;

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

@WebServlet(name = "Restaurants Menu Management", value = "/restaurant-menu-management")
public class restaurantMenuManagement extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null ||  session.getAttribute("accountType") != "restaurant") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        Restaurant resto = (Restaurant) session.getAttribute("user");

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<String, ArrayList<ItemBoxUi>> food_items = resto.get_food_items_ui();


        // Sets attributes for the view
        req.setAttribute("user", resto);
        req.setAttribute("food_items_to_scroll", food_items);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_menu_management.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}