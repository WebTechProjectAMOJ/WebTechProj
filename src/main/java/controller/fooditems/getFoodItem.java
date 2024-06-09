package controller.fooditems;

import dbconnection.DbConnection;
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
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Food Item Details", value = "/get-food-item-details")
public class getFoodItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        String idstr = request.getParameter("id");
        ObjectId id = new ObjectId(idstr);
        Document foodItem = DbConnection.findOne("food_items", new Document("_id", id));
        Document json = new Document();
        json.put("name", foodItem.getString("name"));
        json.put("price", foodItem.getDouble("price"));
        json.put("photo_url", foodItem.getString("photo_url"));
        ArrayList<ObjectId> tagItems = foodItem.get("tags", ArrayList.class);
        String taglist = "";
        for(ObjectId tagid : tagItems){
            Document tag = DbConnection.findOne("tags", new Document("_id", tagid));
            taglist += tag.getString("name") + ", ";
        }
        System.out.println(taglist);
        json.put("description", taglist);
        JsonObject jsonObject = new JsonObject(json.toJson());
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.flush();
    }

    public void destroy() {
    }
}