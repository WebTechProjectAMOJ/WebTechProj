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

@WebServlet(name = "Get All Tools - Driver", value = "/get-all-tool-names")
public class getAllTools extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Document> tags = DbConnection.find("tools", new Document());
        PrintWriter out = response.getWriter();
        for (Document tool : tags) {
            System.out.println(tool.getString("name"));
            out.print(tool.getString("name") + ",");
        }
    }
}
