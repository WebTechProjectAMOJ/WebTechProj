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
import org.bson.Document;

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
        ArrayList<Document> likes = new ArrayList<>();
        Document prefs = new Document();
        for(String s : likesList.split(",")) {
            Document tag = new Document("name", s);
            likes.add(DbConnection.findOne("tags", tag));
        }
        ArrayList<Document> dislikes = new ArrayList<>();
        for(String s : dislikesList.split(",")) {
            Document tag = new Document("name", s);
            dislikes.add(DbConnection.findOne("tags", tag));
        }
        ArrayList<Document> allergens = new ArrayList<>();
        for(String s : allergensList.split(",")) {
            Document tag = new Document("name", s);
            allergens.add(DbConnection.findOne("tags", tag));
        }
        prefs.put("likes", likes);
        prefs.put("dislikes", dislikes);
        prefs.put("allergens", allergens);
        Document user = new Document();
        Document credentials = new Document();
        user.put("preferences", prefs);
        credentials.put("username", username);
        String hashed = Pash.hashPassword(password);
        credentials.put("password", hashed);
        user.put("credentials", credentials);
        user.put("email", email);
        user.put("first_name", firstname);
        user.put("last_name", name);
        ArrayList<String> addresses = new ArrayList<>();
        addresses.add(address);
        user.put("address", addresses);
        user.put("orders", new ArrayList<>());
        System.out.println(user.toJson());
        boolean doc = DbConnection.insertOne("consumers", user);
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