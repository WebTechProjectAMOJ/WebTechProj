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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Show basket", value = "/view-basket")
public class viewBasket extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("accountType") != "customer") {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        HashMap<Restaurant, Order> list = (HashMap<Restaurant, Order>) session.getAttribute("Basket");
        HashMap<Restaurant, ArrayList<ItemBoxUi>> itemHash = new HashMap<>();
        for(Map.Entry<Restaurant, Order> item : list.entrySet()) {
            ArrayList<ItemBoxUi> items = null;
            Restaurant restaurant = item.getKey();
            Order order = item.getValue();
            if(itemHash.containsKey(restaurant)) {
                items = itemHash.get(restaurant);
            }
            else {
                items = new ArrayList<>();
            }
            for(OrderItems ord : order.getOrder_items()){
                Fooditem f = ord.getFooditem();
                ItemBoxUi i = new ItemBoxUi(ord.getQuantity() + "x" + f.getName(),"$" + ord.getPrice(), f.getPhoto_url(),"");
                items.add(i);
            }
            itemHash.put(restaurant, items);
        }
        request.setAttribute("items_to_scroll", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/homepages/consumer_basket.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}