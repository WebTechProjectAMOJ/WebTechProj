package controller.account;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.user.Consumer;
import models.user.Driver;
import models.user.Restaurant;
import models.user.login;
import org.bson.Document;

import java.io.IOException;

@WebServlet(name = "Logout", value = "/logout")
public class Logout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("accountType") == "driver") {
            Document pos = new Document("set", false);
            Driver d = (Driver) session.getAttribute("user");
            DbConnection.setOne("drivers", new Document("_id", d.getId()), "current_pos", pos);
        }
        session.invalidate();
        session = request.getSession(true);
        session.setAttribute("message", "You have been Logged out!");
        response.sendRedirect(request.getContextPath() + "/");
    }
}
