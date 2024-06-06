package controller.driver;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ui_util.ItemBoxUi;
import models.user.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Driver Landing", urlPatterns = {"/driver-landing", "/driver-pending"})
public class driverLanding extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("accountType") != "driver") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        String url = req.getRequestURI().split("/")[req.getRequestURI().split("/").length - 1];

        Driver driver = (Driver) session.getAttribute("user");

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<String, ArrayList<ItemBoxUi>> order_hash = null;

        if (url.equals("driver-landing")) {
            order_hash = driver.getUIHashOrderStatus();
        }

        if (url.equals("driver-pending")) {
            order_hash = driver.get_not_complete_orders();
        }

        // Sets attributes for the view
        req.setAttribute("user", driver);
        req.setAttribute("orders_to_scroll", order_hash);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/driver_orders_pending.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}
