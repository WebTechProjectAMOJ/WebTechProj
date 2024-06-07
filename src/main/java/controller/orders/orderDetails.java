package controller.orders;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ui_util.RatingBoxUi;
import models.user.Consumer;
import models.user.Restaurant;
import org.bson.types.ObjectId;

import java.io.IOException;

@WebServlet(name = "Order", urlPatterns = {"/order"})
public class orderDetails extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        ObjectId order_id = new ObjectId(req.getParameter("id"));
        
        if (session.getAttribute("accountType") == "consumer") {
            Consumer user = (Consumer) session.getAttribute("user");
            // Makes a hashmap with list of all status and a list of all orders
        }


        // Sets attributes for the view

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/order_summary.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}