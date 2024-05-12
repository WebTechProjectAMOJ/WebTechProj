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
import models.user.Driver;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Creating An Account - Restaurants", value = "/create-account-drivers")
public class CreateAccountDrivers extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String firstname = request.getParameter("firstname");
        String email = request.getParameter("Email");
        String toolsList = request.getParameter("tools");
        ArrayList<ObjectId> tools = new ArrayList<>();
        for(String s : toolsList.split(",")) {
            Document tool = new Document("name", s);
            tools.add((ObjectId) DbConnection.findOne("tools", tool).get("_id"));
        }
//        Document user = new Document();
//        Document credentials = new Document();
//        credentials.put("username", username);
        String hashed = Pash.hashPassword(password);
//        credentials.put("password", hashed);
//        user.put("credentials", credentials);
//        user.put("email", email);
//        user.put("first_name", firstname);
//        user.put("name", name);
//        user.put("tools", tools);
//        user.put("ratings", new ArrayList<>());
//        user.put("orders", new ArrayList<>());
//        user.put("current_pos", "");
        Driver d = new Driver(username, hashed, name, email,
                new ArrayList<ObjectId>(), firstname, tools, new ArrayList<ObjectId>(), new JsonObject("{\"set\":false}"));
        boolean doc = d.write();
//        boolean doc = DbConnection.insertOne("drivers", user);
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        HttpSession session = request.getSession(true);
//        session.setAttribute("createdAccount", doc);
        view.forward(request, response);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("/views/AccountCreations/CreateAccountDrivers.jsp");
        view.forward(req, resp);
    }

    public void destroy() {
    }
}