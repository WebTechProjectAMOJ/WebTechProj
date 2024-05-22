package controller.tags;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.delivery.Tool;
import models.tags.Tag;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Add a tag", value = "/add-tag-admin")
public class singleTag extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> tooltype = DbConnection.distinct("tags", "type", null);
        System.out.println(tooltype);
        request.setAttribute("tagtypes", tooltype);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/adminPages/add-tag.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        Tag tag = new Tag(name, type);
        boolean written = tag.write();
        HttpSession session = request.getSession();
        if(written){
            session.setAttribute("adminMessage", "Tag has been created");
        }
        else{
            session.setAttribute("adminMessage", "Tag has not been created");
        }
        response.sendRedirect(request.getContextPath() + "/admin-landing");
    }
}
