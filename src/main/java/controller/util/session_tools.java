package controller.util;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.user.Consumer;
import models.user.Driver;
import models.user.Restaurant;
import models.user.login;
import org.bson.Document;

import java.io.IOException;


public class session_tools {
    static void check_login(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
    }

    public static void rebuild_user_session(HttpSession session) {

        if (session.getAttribute("accountType") == "customer") {
            Consumer user = (Consumer) session.getAttribute("user");
            user = new Consumer(DbConnection.findOne("consumers", new Document("_id", user.getId())));
            session.setAttribute("user", user);
        }

        if (session.getAttribute("accountType") == "restaurant") {
            Restaurant user = (Restaurant) session.getAttribute("user");
            user = new Restaurant(DbConnection.findOne("restaurants", new Document("_id", user.getId())));
            session.setAttribute("user", user);
        }

        if (session.getAttribute("accountType") == "driver") {
            Driver user = (Driver) session.getAttribute("user");
            user = new Driver(DbConnection.findOne("drivers", new Document("_id", user.getId())));
            session.setAttribute("user", user);
        }

    }

}
