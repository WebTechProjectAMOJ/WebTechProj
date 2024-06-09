package controller.messages;

import dbconnection.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.messages.Message;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(name = "Get All Messages", value = "/get-all-messages")
public class getallmessages extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid = request.getParameter("orderid");
        ObjectId orderIdObj = new ObjectId(orderid);
        ArrayList<Document> msg_docs = DbConnection.find("messages", new Document("orderid", orderIdObj));
        ArrayList<Message> msgs = new ArrayList<>();
        for (Document msg_doc : msg_docs) {
            msgs.add(new Message(msg_doc));
        }
        request.setAttribute("msgs", msgs);
        request.getRequestDispatcher("/views/homepages/messages-view.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid = request.getParameter("orderId");
        ObjectId orderIdObj = new ObjectId(orderid);
        String message = request.getParameter("msg");
        String sender = request.getParameter("sender");
        Message msg = new Message(message, sender, orderIdObj);
        System.out.println(msg.toDocument().toJson());
        msg.write();
        response.getWriter().println("Message sent");
    }
}
