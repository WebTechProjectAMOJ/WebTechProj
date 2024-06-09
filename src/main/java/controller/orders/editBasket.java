package controller.orders;

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
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Edit the basket", value = "/edit-basket")
public class editBasket extends HttpServlet {

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session == null || session.getAttribute("accountType") != "customer") {
            out.println("Failed to edit basket!");
            return;
        }
        String itemId = request.getParameter("id");
        ObjectId ordid = new ObjectId(itemId);
        HashMap<Restaurant, Order> list = (HashMap<Restaurant, Order>) session.getAttribute("Basket");
        for(Map.Entry<Restaurant, Order> item : list.entrySet()) {
            Order order = item.getValue();
            if (order.removeItem(ordid))
                if(order.getOrder_items().isEmpty()){
                    list.remove(item.getKey());
                    session.setAttribute("Basket", list);
                }
            break;
        }
        out.println("Done!");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session == null || session.getAttribute("accountType") != "customer") {
            out.println("Failed to edit basket!");
            return;
        }
        String itemId = request.getParameter("id");
        String qty = request.getParameter("qty");
        int quantity = Integer.parseInt(qty);
        String customizations = request.getParameter("cust");
        ObjectId ordid = new ObjectId(itemId);
        HashMap<Restaurant, Order> list = (HashMap<Restaurant, Order>) session.getAttribute("Basket");
        for(Map.Entry<Restaurant, Order> item : list.entrySet()) {
            Order order = item.getValue();
            if (order.editOrder(ordid, customizations, quantity))
                break;
        }
        out.println("Done!");
    }

    public void destroy() {
    }
}