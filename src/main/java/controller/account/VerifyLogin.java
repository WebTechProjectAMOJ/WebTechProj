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
import models.user.Driver;
import models.user.Restaurant;
import models.user.login;
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
        login obj = tryValidate(accountType, object);
        RequestDispatcher view = null;
        HttpSession session = request.getSession(true);
        session.setAttribute("failedLogin", false);
        boolean pass = false;
        if (obj != null){
            boolean doc = obj.verify(password);
            response.getWriter().println(doc ? "Yes" : "No");
            session.setAttribute("failedLogin", !doc);
            if (doc) {
                String link= obj.getAccountType();
                session.setAttribute("user", obj);
                session.setAttribute("accountType", link);
                response.sendRedirect(request.getContextPath() + "/" + link + "-landing");
                pass = true;
            }
        }
        if(!pass) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
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
