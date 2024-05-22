package controller.tags;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.delivery.DeliveryService;
import models.tags.Tag;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Add a Delivery Service", value = "/add-delivery-service-admin")
public class singleDeliveryService extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/adminPages/add-delivery-service.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double fee = Double.parseDouble(request.getParameter("fee"));
        DeliveryService s  = new DeliveryService(name, fee, null);
        boolean written = s.write();
        HttpSession session = request.getSession();
        if(written){
            session.setAttribute("adminMessage", "Service has been created");
        }
        else{
            session.setAttribute("adminMessage", "Service has not been created");
        }
        response.sendRedirect(request.getContextPath() + "/admin-landing");
    }
}
