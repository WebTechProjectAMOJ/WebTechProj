package org.example.webtechproj;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

public class TestCloudinary {
    static Dotenv dotenv = Dotenv.load();

    static Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));

    public static void main(String[] args) {
        cloudinary.config.secure = true;
        System.out.println(cloudinary.config.cloudName);
    }
}
