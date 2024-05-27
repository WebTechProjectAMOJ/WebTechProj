package controller.orders;

import dbconnection.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Add order to basket", value = "/add-to-basket")
public class addToBasket extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ArrayList<HashMap<String, String>> getList = (ArrayList<HashMap<String, String>>) session.getAttribute("currentbasket");
        HashMap<String, String> orde = new HashMap<>();
        String name = request.getParameter("name");
        String cost = request.getParameter("cost");
        String quantity = request.getParameter("quantity");
        String forwardto = request.getParameter("forwardto");
        String customization = request.getParameter("custom");
        String photo_url = request.getParameter("photo_url");
        orde.put("name", name);
        orde.put("cost", cost);
        orde.put("quantity", quantity);
        orde.put("customization", customization);
        orde.put("restaurant", forwardto);
        orde.put("photo_url", photo_url);
        getList.add(orde);
        session.setAttribute("currentbasket", getList);
        response.sendRedirect(request.getContextPath() + "/restaurant?id=" + forwardto);
    }

    public void destroy() {
    }
}