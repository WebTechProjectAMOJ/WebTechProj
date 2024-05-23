package controller.restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.foodItems.Fooditem;
import models.ui_util.ItemBoxUi;
import models.user.Restaurant;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Restaurants Menu", value = "/restaurant")
public class restaurantDisplay extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        String idstr = req.getParameter("id");
        ObjectId id = new ObjectId(idstr);
        Restaurant restaurant = new Restaurant(id);

        // Makes a hashmap with list of all status and a list of all orders
        HashMap<String, ArrayList<ItemBoxUi>> itemHash = new HashMap<>();
        ArrayList< Fooditem> foodlist = restaurant.getFoodItemList();
        ArrayList<ItemBoxUi> itemlist = new ArrayList<ItemBoxUi>();

        for(Fooditem fooditem : foodlist) {
            itemlist.add(fooditem.getUiItemBox());
        }

        itemHash.put("1", itemlist);

//        // Sets attributes for the view
        req.setAttribute("currentRestaurant", restaurant);
        req.setAttribute("items_to_scroll", itemHash);
//
        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/restaurant_menus.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}