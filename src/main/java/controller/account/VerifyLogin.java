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

import java.io.IOException;

@WebServlet(name = "Verify Details", value = "/verify-login")
public class VerifyLogin extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String accountType = request.getParameter("account-type");
        System.out.println(username + " " + password + " " + accountType);
        Document object = new Document();
        object.put("credentials.username", username);
        Document user = null;
        String link="";
        switch (accountType) {
            case "Customers":{
                user = DbConnection.findOne("consumers", object);
                link = "customer";
                break;
            }
            case "Restaurants":{
                user = DbConnection.findOne("restaurants", object);
                link = "restaurant";
                break;
            }
            case "Drivers":{
                user = DbConnection.findOne("drivers", object);
                link = "driver";
                break;
            }
        }
        RequestDispatcher view = null;
        HttpSession session = request.getSession(true);
        session.setAttribute("failedLogin", false);
        boolean pass = false;
        if (user != null){
            Document creds = (Document) user.get("credentials");
            boolean doc = Pash.verify(creds.getString("password"), password);
            response.getWriter().println(doc ? "Yes" : "No");
            session.setAttribute("failedLogin", !doc);
            if (doc) {
                Consumer u = new Consumer(user);
                session.setAttribute("user", u);
                session.setAttribute("accountType", link);
                response.sendRedirect(request.getContextPath() + "/home-" + link);
                pass = true;
            }
        }
        if(!pass) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
