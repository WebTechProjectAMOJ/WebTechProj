package controller.restaurant;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.tickets.Ticket;
import models.user.Restaurant;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Restaurants Create Ticket", value = "/restaurant-tickets")
public class restaurantTicket extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null ||  session.getAttribute("accountType") != "restaurant") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        Restaurant resto = (Restaurant) session.getAttribute("user");
        ArrayList<Document> ticketdocs = DbConnection.find("tickets", new Document("restaurant_id", resto.getId()));
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (Document doc : ticketdocs) {
            tickets.add(new Ticket(doc));
        }
        req.setAttribute("tickets", tickets);
        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_ticket_menu.jsp");

        dispatcher.forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null ||  session.getAttribute("accountType") != "restaurant") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        Restaurant resto = (Restaurant) session.getAttribute("user");
        String title = req.getParameter("subject");
        String description = req.getParameter("description");
        Ticket ticket = new Ticket(title, description, resto.getId());
        if(ticket.write()){
            session.setAttribute("message", "Ticket created");
        }
        resp.sendRedirect(req.getContextPath() + "/restaurant-tickets");
    }

    public void destroy() {
    }
}