package controller.restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ui_util.ItemBoxUi;
import models.ui_util.RatingBoxUi;
import models.user.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Restaurants Reviews", value = "/restaurant-reviews")
public class restaurantReview extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null ||  session.getAttribute("accountType") != "restaurant") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        Restaurant resto = (Restaurant) session.getAttribute("user");

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<String, ArrayList<RatingBoxUi>> reviews_hash = resto.get_food_item_reviews();

        // Sets attributes for the view
        req.setAttribute("reviews_to_scroll", reviews_hash);


        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_reviews.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}