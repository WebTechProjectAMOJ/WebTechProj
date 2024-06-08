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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Set Driver Location", urlPatterns = {"/set-driver-current-location"})
public class setDriverLocation extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Double latitude = Double.valueOf(req.getParameter("latitude"));
        Double longitude = Double.valueOf(req.getParameter("longitude"));
        HttpSession session = req.getSession(false);
        Driver d = (Driver) session.getAttribute("user");
        Document position = new Document();
        position.append("latitude", latitude);
        position.append("longitude", longitude);
        position.append("set", true);
        d.setCurrentPos(position);
        DbConnection.setOne("drivers", new Document("_id", d.getId()), "current_pos", position);
        resp.getWriter().write("Success");
    }

    public void destroy() {
    }
}
