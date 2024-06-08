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
import models.user.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;


@WebServlet(name = "Finalize the order", value = "/finalize-order")
public class closeOrder extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String orderidstr = request.getParameter("order_id");
        ObjectId orderid = new ObjectId(orderidstr);
        Order order = new Order(DbConnection.findOne("orders", new Document("_id", orderid)));
        order.setStatus("done");
        response.getWriter().println("Order closed!");
    }

    public void destroy() {
    }
}