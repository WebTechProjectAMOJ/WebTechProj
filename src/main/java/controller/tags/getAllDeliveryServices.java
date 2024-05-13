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

@WebServlet(name = "Get All Delivery Services", value = "/get-all-delivery-service-names")
public class getAllDeliveryServices extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Document> services = DbConnection.find("delivery_services", new Document());
        PrintWriter out = response.getWriter();
        for (Document service : services) {
            System.out.println(service.getString("name"));
            out.print(service.getString("name") + ",");
        }
    }
}
