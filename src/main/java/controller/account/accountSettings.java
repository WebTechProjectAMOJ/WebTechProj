package controller.account;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.user.Consumer;

import java.io.IOException;


@WebServlet(name = "Consumer Settings", value = "/account-settings")
public class accountSettings extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Check if login
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if (session.getAttribute("accountType") == "customer") {
            Consumer user = (Consumer) session.getAttribute("user");


            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/homepages/consumer_account.jsp");

            dispatcher.forward(req, resp);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect(request.getContextPath() + "/account-settings");
    }

    public void destroy() {
    }
}