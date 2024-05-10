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

@WebServlet(name = "Creating An Account - Drivers", value = "/create-account-restaurants")
public class CreateAccountRestaurants extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("Email");
        String tagsList = request.getParameter("Tags");
        String address = request.getParameter("placedata");

        Document restaurant = new Document();
        Document credentials = new Document();
        credentials.put("username", username);
        String hashed = Pash.hashPassword(password);
        credentials.put("password", hashed);
        restaurant.put("credentials", credentials);
        restaurant.put("food_items", new ArrayList<>());
        restaurant.put("combos", new ArrayList<>());
        restaurant.put("name", name);
        restaurant.put("email", email);
        restaurant.put("ratings",new ArrayList<>());
        restaurant.put("address", address);
        restaurant.put("delivery_services", new ArrayList<>());
        restaurant.put("orders", new ArrayList<>());
        ArrayList<Document> tags = new ArrayList<>();
        for(String s : tagsList.split(",")) {
            Document tag = new Document("name", s);
            tags.add(DbConnection.findOne("tags", tag));
        }
        restaurant.put("tags", tags);


        boolean doc = DbConnection.insertOne("restaurants", restaurant);
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