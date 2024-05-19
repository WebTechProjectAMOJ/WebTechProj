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
import models.user.Consumer;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Creating An Account - Customer", value = "/create-account-customers")
public class CreateAccountCustomers extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String firstname = request.getParameter("firstname");
        String email = request.getParameter("Email");
        String likesList = request.getParameter("likes");
        String dislikesList = request.getParameter("dislikes");
        String allergensList = request.getParameter("allergens");
        String address = request.getParameter("placedata");
        ArrayList<ObjectId> likes = new ArrayList<>();
        Document prefs = new Document();
        for(String s : likesList.split(",")) {
            Document tag = new Document("name", s);
            likes.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
        }
        ArrayList<ObjectId> dislikes = new ArrayList<>();
        for(String s : dislikesList.split(",")) {
            Document tag = new Document("name", s);
            dislikes.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
        }
        ArrayList<ObjectId> allergens = new ArrayList<>();
        for(String s : allergensList.split(",")) {
            Document tag = new Document("name", s);
            allergens.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
        }
        prefs.put("likes", likes);
        prefs.put("dislikes", dislikes);
        prefs.put("allergens", allergens);
        ArrayList<JsonObject> addresses = new ArrayList<>();
        addresses.add(new JsonObject(address));
        String hashed = Pash.hashPassword(password);
        Consumer c = new Consumer(username, hashed, name, email, prefs, new ArrayList<ObjectId>(), firstname, addresses);
        boolean doc = c.write();
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        HttpSession session = request.getSession(true);
        session.setAttribute("createdAccount", doc);
        view.forward(request, response);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("/views/AccountCreations/CreateAccountCustomers.jsp");
        view.forward(req, resp);
    }

    public void destroy() {
    }
}