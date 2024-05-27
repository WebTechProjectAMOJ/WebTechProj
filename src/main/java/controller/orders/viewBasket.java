package controller.orders;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ui_util.ItemBoxUi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Show basket", value = "/view-basket")
public class viewBasket extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashMap<String, ArrayList<ItemBoxUi>> resto_hash = new HashMap<String, ArrayList<ItemBoxUi>>();
        HttpSession session = request.getSession(false);
        ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) session.getAttribute("currentbasket");
        HashMap<String, ArrayList<ItemBoxUi>> itemHash = new HashMap<>();
        for(HashMap<String, String> item : list) {
            ItemBoxUi i = new ItemBoxUi(item.get("name"),item.get("cost") + "x" + item.get("quantity"),item.get("photo_url"),"");
            String resname = item.get("forwardto");
            ArrayList<ItemBoxUi> items = new ArrayList<>();
            if(itemHash.containsKey(resname)) {
                items = itemHash.get(resname);
            }
            items.add(i);
            itemHash.put(resname, items);
        }
        request.setAttribute("items_to_scroll", itemHash);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/homepages/consumer_basket.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}