package controller.driver;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ui_util.ItemBoxUi;
import models.ui_util.RatingBoxUi;
import models.user.Driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "Driver Reviews", urlPatterns = {"/driver-reviews"})
public class driverReviews extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("accountType") != "driver") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        Driver driver = (Driver) session.getAttribute("user");

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<Integer, ArrayList<RatingBoxUi>> reviews_to_scroll = driver.getReviewsHash();

        // Sets attributes for the view
        req.setAttribute("user", driver);
        req.setAttribute("reviews_to_scroll", reviews_to_scroll);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/driver_reviews.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}