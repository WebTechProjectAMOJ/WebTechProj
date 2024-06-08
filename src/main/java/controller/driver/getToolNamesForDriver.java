package controller.driver;

import dbconnection.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.user.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Get Tool Names for a driver", urlPatterns = {"/get-driver-tool-names"})
public class getToolNamesForDriver extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String driverIdStr = req.getParameter("driver_id");
        ObjectId driverId = new ObjectId(driverIdStr);
        System.out.println(driverId);
        Document DriverDoc = DbConnection.findOne("drivers", new Document("_id", driverId));
        if (DriverDoc == null) {return;}
        Driver d = new Driver(DriverDoc);
        ArrayList<ObjectId> toolids = d.getTools();
        StringBuilder output = new StringBuilder();
        for (ObjectId toolid : toolids) {
            Document toolDoc = DbConnection.findOne("tools", new Document("_id", toolid));
            output.append(toolDoc.getString("name")).append(",");
        }
        resp.getWriter().println(output.toString());
    }

    public void destroy() {
    }
}
