package controller.fooditems;

import dbconnection.Cloudinary;
import dbconnection.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.foodItems.Fooditem;
import models.user.Restaurant;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@WebServlet(name = "makefooditem", value = "/create-food-item")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class createFoodItem extends HttpServlet {
    private String fakeImage = "https://static.vecteezy.com/system/resources/previews/006/411/068/original/food-testing-black-glyph-icon-nutrition-safety-and-quality-check-sampling-process-product-analysis-chemical-and-nutritional-research-silhouette-symbol-on-white-space-isolated-illustration-vector.jpg";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("imageFile");
        String imageUrl = "";
        if (filePart != null) {
            File file =  File.createTempFile("tmp", null);
            try (InputStream input = filePart.getInputStream()) {
                FileUtils.copyInputStreamToFile(input, file);
                imageUrl = Cloudinary.writeToCloudinary(file);
            }
            catch (RuntimeException R) {
                imageUrl = fakeImage;
            }
        }
        else {
            imageUrl = fakeImage;
        }
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String tagList = request.getParameter("likes");
        ArrayList<ObjectId> tags = new ArrayList<ObjectId>();
        for(String s : tagList.split(",")) {
            Document tag = new Document("name", s);
            tags.add((ObjectId) DbConnection.findOne("tags", tag).get("_id"));
        }
        ArrayList<ObjectId> tools = new ArrayList<>();
        String toolsList = request.getParameter("tools");
        for(String s : toolsList.split(",")) {
            Document tool = new Document("name", s);
            tools.add((ObjectId) DbConnection.findOne("tools", tool).get("_id"));
        }
        Fooditem f = new Fooditem(name, price, tags, tools, imageUrl, new ArrayList<ObjectId>());
        HttpSession session = request.getSession(false);
        Restaurant res = (Restaurant) session.getAttribute("user");
        boolean written = f.write(res);
        if(written){
            session.setAttribute("message", "Successfully added food item!");
        }
        else{
            session.setAttribute("message", "Failed to add food item!");
        }
        response.sendRedirect("./restaurant-landing");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/views/createFooditem/createFoodItem.jsp");
        view.forward(request, response);
    }
}
