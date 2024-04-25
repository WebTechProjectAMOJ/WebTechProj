package org.example.webtechproj;

import dbconnection.DbConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "TestingTheConnection", value = "/test-connection")
public class TestDBConnection extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        Document searchQuery = new Document();
        ArrayList<Document> doc = DbConnection.find("testData",searchQuery);
        String output = "";
        for (Document d : doc){
            output = output.concat(d.toJson());
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" +  output + "</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        int num = Integer.parseInt(request.getParameter("no"));
        System.out.println(num);
        Document searchQuery = new Document();
        searchQuery.put("x", num);
        Document doc = DbConnection.findOne("testData",searchQuery);
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + doc.toJson() + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}