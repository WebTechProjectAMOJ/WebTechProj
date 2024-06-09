package controller.orders;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Get Order-Details", value = "/order-details")
public class orderDetailsPage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String order_id = request.getParameter("orderid");
        ObjectId orderId = new ObjectId(order_id);
        Document orderDoc = DbConnection.findOne("orders", new Document("_id", orderId));
        Order order = new Order(orderDoc);
        request.setAttribute("orderDetails", order);
        request.setAttribute("orderId", orderId.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/homepages/order-details.jsp");
        dispatcher.forward(request, response);
    }

}