package controller.views;
import dbconnection.DbConnection;
import models.ratings.Rating;
import org.bson.Document;

import javax.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: WebTechProj
 * @description:
 * @author: Jintao
 * @create: 2024-05-27 14:14
 **/
@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Document> reviews = DbConnection.find("ratings", new Document());
        ArrayList<Rating> ratings = new ArrayList<>();
        for (Document review : reviews) {
            ratings.add(new Rating(review));
        }
        request.setAttribute("ratings", ratings);
        request.getRequestDispatcher("/views/reviews.jsp").forward(request, response);
    }
}
