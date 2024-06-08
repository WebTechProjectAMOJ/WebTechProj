package controller.orders;

import com.mongodb.BasicDBObject;
import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.foodItems.Fooditem;
import models.order.Order;
import models.order.OrderItems;
import models.ui_util.ItemBoxUi;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Change Order Status", value = "/change-order-status")
public class changeOrderStatus extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String type = request.getParameter("type");
        String hash = request.getParameter("hash");
        ObjectId order_id = new ObjectId(hash);
        switch (type) {
            case "accept":{
                DbConnection.setOne("orders", new Document("_id", order_id), "status", "accepted");
                Document orderDoc = DbConnection.findOne("orders", new Document("_id", order_id));
                ObjectId resto_id = orderDoc.getObjectId("restaurant");
                DbConnection.updateOne("restaurants", new Document("_id", resto_id),
                        new BasicDBObject("$push", new Document("orders", order_id)));
                break;
            }
            case "reject":{
                DbConnection.setOne("orders", new Document("_id", order_id), "status", "rejected");
                break;
            }
        }
        response.getWriter().println(1);
    }

    public void destroy() {
    }
}