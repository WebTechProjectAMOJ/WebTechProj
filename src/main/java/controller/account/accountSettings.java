package controller.account;

import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.user.Consumer;
import models.user.Restaurant;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Consumer Settings", value = "/account-settings")
public class accountSettings extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Check if login
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if (session.getAttribute("accountType") == "customer") {
            Consumer user = (Consumer) session.getAttribute("user");


            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/homepages/consumer_account.jsp");

            dispatcher.forward(req, resp);
        }

        if (session.getAttribute("accountType") == "restaurant") {
            Restaurant user = (Restaurant) session.getAttribute("user");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/homepages/restaurant_account.jsp");

            dispatcher.forward(req, resp);
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        if (session.getAttribute("accountType") == "customer") {
            Consumer user = (Consumer) session.getAttribute("user");

            if (req.getParameter("username") != null && !req.getParameter("username").isEmpty()) {
                user.setUsername(req.getParameter("username"));
            }

            if (req.getParameter("name") != null && !req.getParameter("name").isEmpty()) {
                user.setName(req.getParameter("name"));
            }
            if (req.getParameter("first_name") != null && !req.getParameter("first_name").isEmpty()) {
                user.setName(req.getParameter("first_name"));
            }
            if (req.getParameter("email") != null && !req.getParameter("email").isEmpty()) {
                user.setName(req.getParameter("email"));
            }
            if (req.getParameter("password") != null && !req.getParameter("password").isEmpty()) {
                user.setPassword(req.getParameter("password"));
            }
            if (req.getParameter("placedata") != null) {
                ArrayList<Object> addresses = user.getAddress();
                addresses.add(Document.parse(req.getParameter("placedata")));
                user.setAddress(addresses);
            }

            Document prefs = user.getPreferences();

            if (req.getParameter("likes") != null) {
                String likesList = req.getParameter("likes");
                ArrayList<ObjectId> likes = new ArrayList<>();
                for (String s : likesList.split(",")) {
                    Document tag = new Document("name", s);
                    likes.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
                }
                prefs.remove("likes");
                prefs.put("likes", likes);
                user.setPreferences(prefs);
            }


            if (req.getParameter("dislikes") != null) {
                String dislikesList = req.getParameter("dislikes");
                ArrayList<ObjectId> dislikes = new ArrayList<>();
                for (String s : dislikesList.split(",")) {
                    Document tag = new Document("name", s);
                    dislikes.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
                }
                prefs.remove("dislikes");
                prefs.put("dislikes", dislikes);
                user.setPreferences(prefs);
            }

            if (req.getParameter("allergens") != null) {
                String allergensList = req.getParameter("allergens");
                ArrayList<ObjectId> allergens = new ArrayList<>();
                for (String s : allergensList.split(",")) {
                    Document tag = new Document("name", s);
                    allergens.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
                }
                prefs.remove("allergens");
                prefs.put("allergens", allergens);
                user.setPreferences(prefs);
            }

            // TODO: Add the removal of an address

            session.setAttribute("user", user.update());
        }


        if (session.getAttribute("accountType") == "restaurant") {
            Restaurant user = (Restaurant) session.getAttribute("user");

            if (req.getParameter("username") != null && !req.getParameter("username").isEmpty()) {
                user.setUsername(req.getParameter("username"));
            }

            if (req.getParameter("password") != null && !req.getParameter("password").isEmpty()) {
                user.setPassword(req.getParameter("password"));
            }
            if (req.getParameter("email") != null && !req.getParameter("email").isEmpty()) {
                user.setName(req.getParameter("email"));
            }
            if (req.getParameter("name") != null && !req.getParameter("name").isEmpty()) {
                user.setName(req.getParameter("name"));
            }

            if (req.getParameter("Tags") != null) {
                String tagsList = req.getParameter("Tags");
                ArrayList<ObjectId> tags = new ArrayList<>();
                for (String s : tagsList.split(",")) {
                    Document tag = new Document("name", s);
                    tags.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
                }
                user.setTags(tags);
            }

            if (req.getParameter("placedata") != null) {
                user.setAddress(Document.parse(req.getParameter("placedata")));
            }

            session.setAttribute("user", user.update());
        }


        resp.sendRedirect(req.getContextPath() + "/account-settings");
    }

    public void destroy() {
    }
}