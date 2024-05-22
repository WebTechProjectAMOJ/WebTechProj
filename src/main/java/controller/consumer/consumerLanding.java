package controller.consumer;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.order.Order;
import models.tags.Tag;
import models.ui_util.ItemBoxUi;
import models.user.Consumer;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Consumer Landing", value = "/customer-landing")
public class consumerLanding extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Gets all tags
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("accountType") != "customer") {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        Document to_find = new Document("type", "preference");
        ArrayList<Document> found = DbConnection.find(
                "tags",
                to_find
        );

        HashMap<String, ArrayList<ItemBoxUi>> resto_hash = new HashMap<String, ArrayList<ItemBoxUi>>();
        for (Document tag_doc : found) {
            Tag tag = new Tag(tag_doc);

            Document resto_find = new Document("tags", tag.getId());
            ArrayList<Document> found_resto_docs = DbConnection.find(
                    "restaurants",
                    resto_find
            );

            ArrayList<ItemBoxUi> restos_filtered = new ArrayList<ItemBoxUi>();
            for (Document rest_doc : found_resto_docs) {
                Restaurant resto = new Restaurant(rest_doc);
                restos_filtered.add(resto.getUiItemBox());
            }

            if (resto_hash.get(tag.getName()) != null) {
                resto_hash.get(tag.getName()).addAll(restos_filtered);
            } else {
                ArrayList<ItemBoxUi> new_cat = new ArrayList<ItemBoxUi>(restos_filtered);
                resto_hash.put(tag.getName(), new_cat);
            }
        }


        // Sets attributes for the view
        req.setAttribute("items_to_scroll", resto_hash);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("/views/homepages/consumer_dashboard.jsp");

        dispatcher.forward(req, resp);

    }

    public void destroy() {
    }
}
