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
import models.user.*;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "Verify Details", value = "/verify-login")
public class VerifyLogin extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        session = request.getSession(true);
        if (Objects.equals(username, "admin") || Objects.equals(password, "admin")) {
            User u = new User("admin","admin","admin","admin@admin",null,"admin");
            session.setAttribute("user", u);
            session.setAttribute("admin", true);
            response.sendRedirect(request.getContextPath() + "/admin-landing");
            return;
        }
        if(session.getAttribute("currentbasket") == null) {
            session.setAttribute("currentbasket", new ArrayList<>());
        }
        String accountType = request.getParameter("account-type");
        Document object = new Document();
        object.put("credentials.username", username);
        login obj = tryValidate(accountType, object);
        RequestDispatcher view = null;
        boolean pass = false;
        if (obj != null){
            boolean doc = obj.verify(password);
            response.getWriter().println(doc ? "Yes" : "No");
            if (doc) {
                String link= obj.getAccountType();
                session.setAttribute("user", obj);
                session.setAttribute("accountType", link);
                session.setAttribute("admin", false);
                System.out.println(request.getContextPath() + "/" + link + "-landing");
                response.sendRedirect(request.getContextPath() + "/" + link + "-landing");
                pass = true;
            }
        }
        if(!pass) {
            session.setAttribute("errorMessage", "Invalid Credentials!");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    private login tryValidate(String accountType, Document object) {
        Document user = null;
        login obj = null;
        switch (accountType) {
            case "Customers":{
                user = DbConnection.findOne("consumers", object);
                if (user == null) {
                    return obj;
                }
                obj = new Consumer(user);
                break;
            }
            case "Restaurants":{
                user = DbConnection.findOne("restaurants", object);
                if (user == null) {
                    return obj;
                }
                obj = new Restaurant(user);
                break;
            }
            case "Drivers":{
                user = DbConnection.findOne("drivers", object);
                if (user == null) {
                    return obj;
                }
                obj = new Driver(user);
                break;
            }
        }
        return obj;
    }
}
