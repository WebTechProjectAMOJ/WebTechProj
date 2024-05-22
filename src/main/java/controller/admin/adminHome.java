package controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Admin-Landing", value = "/admin-landing")
public class adminHome extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null || (boolean) session.getAttribute("admin") == false) {
            session.setAttribute("errorMessage", "You're not an Admin!");
            response.sendRedirect(request.getContextPath() + "/");
        }
        else{
            RequestDispatcher view = request.getRequestDispatcher("./views/homepages/admin-home-page.jsp");
            view.forward(request, response);
        }
    }
}
