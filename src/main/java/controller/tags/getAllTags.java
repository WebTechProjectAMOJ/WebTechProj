package controller.tags;

import dbconnection.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Get All Tags", value = "/get-all-tag-names")
public class getAllTags extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        ArrayList<Document> tags = DbConnection.find("tags", new Document("type", type));
        PrintWriter out = response.getWriter();
        for (Document tag : tags) {
            System.out.println(tag.getString("name"));
            out.print(tag.getString("name") + ",");
        }
    }
}
