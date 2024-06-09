package controller.admin;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.tickets.Ticket;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Admin-TicketView", value = "/admin-tickets")
public class viewTickets extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") == null || (boolean) session.getAttribute("admin") == false) {
            session.setAttribute("errorMessage", "You're not an Admin!");
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        ArrayList<Document> ticketdocs = DbConnection.find("tickets", new Document("status", "done"));
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (Document doc : ticketdocs) {
            tickets.add(new Ticket(doc));
        }
        request.setAttribute("tickets", tickets);
        ticketdocs = DbConnection.find("tickets", new Document("status", "open"));
        tickets = new ArrayList<>();
        for (Document doc : ticketdocs) {
            tickets.add(new Ticket(doc));
        }
        request.setAttribute("newtickets", tickets);
        RequestDispatcher view = request.getRequestDispatcher("./views/homepages/admin_tickets.jsp");
            view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tktstr = req.getParameter("id");
        ObjectId tkt = new ObjectId(tktstr);
        System.out.println(tkt);
        DbConnection.setOne("tickets", new Document("_id", tkt), "status", "done");
        resp.getWriter().println("Ticket Done!");
    }
}
