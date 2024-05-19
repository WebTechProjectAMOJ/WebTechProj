package dbconnection;

import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Cloudinary {
    static Dotenv dotenv = Dotenv.load();

    private static com.cloudinary.Cloudinary cloudinary = new com.cloudinary.Cloudinary(dotenv.get("CLOUDINARY_URL"));

    public static String writeToCloudinary(File file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        String imageUrl = (String) uploadResult.get("url");
        return imageUrl;
    }
}
