package controller.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.user.login;

import java.io.IOException;

@WebServlet(name = "Login Page", value = "")
public class loginPage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("user") != null) {
                response.sendRedirect(request.getContextPath() + "/" + session.getAttribute("accountType") + "-landing");
            }
        }
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }
}