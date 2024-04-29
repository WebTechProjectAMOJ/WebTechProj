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
import models.user.User;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Verify Details", value = "/verify-login")
public class VerifyLogin extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Document object = new Document();
        object.put("username", username);
        Document user = DbConnection.findOne("testLogin", object);
        RequestDispatcher view = null;
        HttpSession session = request.getSession(true);
        session.setAttribute("failedLogin", false);
        boolean pass = false;
        if (user != null){
            boolean doc = Pash.verify(user.getString("password"), password);
            session.setAttribute("failedLogin", !doc);
            if (doc) {
                User u = new User(user);
                session.setAttribute("user", u);
                response.sendRedirect(request.getContextPath() + "/views/homepages/homepage1.jsp");
                pass = true;
            }
        }
        if(!pass) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
