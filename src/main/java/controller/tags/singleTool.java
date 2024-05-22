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
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Add a tool", value = "/add-tool-admin")
public class singleTool extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> tooltype = DbConnection.distinct("tools", "type", null);
        request.setAttribute("tooltype", tooltype);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/adminPages/add-tool.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        Tool tool = new Tool(name, type);
        boolean written = tool.write();
        HttpSession session = request.getSession();
        if(written){
            session.setAttribute("adminMessage", "Tool has been created");
        }
        else{
            session.setAttribute("adminMessage", "Tool has not been created");
        }
        response.sendRedirect(request.getContextPath() + "/admin-landing");
    }
}
