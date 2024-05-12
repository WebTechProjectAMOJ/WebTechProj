package controller.account;

import dbconnection.DbConnection;
import dbconnection.Pash;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Creating An Account - Drivers", value = "/create-account-restaurants")
public class CreateAccountRestaurants extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("Email");
        String tagsList = request.getParameter("Tags");
        String address = request.getParameter("placedata");
        String hashed = Pash.hashPassword(password);
        ArrayList<ObjectId> tags = new ArrayList<>();
        for(String s : tagsList.split(",")) {
            Document tag = new Document("name", s);
            tags.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
        }
        Restaurant res = new Restaurant(username, hashed, name, email,new ArrayList<ObjectId>(),
                new ArrayList<ObjectId>(), new ArrayList<ObjectId>(), new ArrayList<ObjectId>(),
                new ArrayList<ObjectId>(), new ArrayList<ObjectId>(), new JsonObject(address));
        boolean doc = res.write();
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        HttpSession session = request.getSession(true);
        session.setAttribute("createdAccount", doc);
        view.forward(request, response);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("/views/AccountCreations/CreateAccountRestaurants.jsp");
        view.forward(req, resp);
    }

    public void destroy() {
    }
}