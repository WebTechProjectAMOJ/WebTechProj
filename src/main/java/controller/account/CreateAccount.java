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
import java.io.PrintWriter;

@WebServlet(name = "Creating An Account", value = "/create-account")
public class CreateAccount extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        Document object = new Document();
        object.put("username", username);
        String hashed = Pash.hashPassword(password);
        object.put("password", hashed);
        object.put("name", name);
        boolean doc = DbConnection.insertOne("testLogin", object);
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