package controller.account;

import dbconnection.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;

@WebServlet(name = "Utility to check existing values", value = "/get-number")
public class CheckCount extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        System.out.println(key);
        System.out.println(value);
        long returnValue = -1;
        switch (key){
            case "username":
            {
                Document doc = new Document("credentials.username",value);
                System.out.println(doc.toJson());
                returnValue = DbConnection.getCount("consumers", doc);
                break;
            }
            case "email":
            {
                Document doc = new Document("email",value);
                System.out.println(doc.toJson());
                returnValue = DbConnection.getCount("consumers", doc);
                break;
            }
        }
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().print("{" + "count :" + returnValue+ "}");
        response.getWriter().print(returnValue);
    }
}
