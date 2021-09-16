package com.codecool.shop.model;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.model.products.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Util {
    public static String readDataFromFile(String filePath) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        StringBuilder jsonText = new StringBuilder();
        String line = reader.readLine();
        while (line != null){
            jsonText.append(line);
            line = reader.readLine();
        }
        reader.close();

        return jsonText.toString();
    }

    public static String getJsonBodyOutOfFetch(HttpServletRequest request) {
        String jsonString = ""; // this is your data sent from client
        try {
            String line = "";
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jsonString += line;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public static List<List<Product>> createObjectsFromJson(String jsonText) throws IOException {

        List<List<Product>> products = new ArrayList<>();
        products.add(getJsonOfCloud(jsonText));
        products.add(getJsonOfOs(jsonText));
        products.add(getJsonOfIDE(jsonText));
        products.add(getJsonOfWorkTool(jsonText));

        return products;
    }

    public static Product createObjectFromJson(String jsonText) {
        List<List<Product>> products = new ArrayList<>();
        products.add(getJsonOfCloud(jsonText));
        products.add(getJsonOfOs(jsonText));
        products.add(getJsonOfIDE(jsonText));
        products.add(getJsonOfWorkTool(jsonText));

        for (List<Product> productList : products) {
            if (productList.size() > 0) return productList.get(0);
        }
        return null;
    }

    private static List<Product> getJsonOfCloud(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();

        Cloud[] cloudProducts = customGson.fromJson(jsonString, Cloud[].class);
        List<Product> finalCloudProducts = new ArrayList<>(Arrays.asList(cloudProducts));

        return finalCloudProducts.stream()
                .filter(element -> element.getProductCategory().getName().equals("Cloud"))
                .collect(Collectors.toList());
    }

    private static List<Product> getJsonOfOs(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();

        OS[] OSProducts = customGson.fromJson(jsonString, OS[].class);
        List<Product> finalOSProducts = new ArrayList<>(Arrays.asList(OSProducts));
        finalOSProducts.removeAll(Collections.singleton(null));

        return finalOSProducts.stream()
                .filter(element -> element.getProductCategory().getName().equals("OS"))
                .collect(Collectors.toList());
    }
    //
    private static List<Product> getJsonOfIDE(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();


        IDE[] IDEProducts = customGson.fromJson(jsonString, IDE[].class);
        List<Product> finalIDEProducts = new ArrayList<>(Arrays.asList(IDEProducts));
        finalIDEProducts.removeAll(Collections.singleton(null));

        return finalIDEProducts.stream()
                .filter(element -> element.getProductCategory().getName().equals("IDE"))
                .collect(Collectors.toList());
    }

    private static List<Product> getJsonOfWorkTool(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson customGson = gsonBuilder.create();


        WorkTool[] WorkToolProducts = customGson.fromJson(jsonString, WorkTool[].class);
        List<Product> finalWorkToolProducts = new ArrayList<>(Arrays.asList(WorkToolProducts));
        finalWorkToolProducts.removeAll(Collections.singleton(null));

        return finalWorkToolProducts.stream()
                .filter(element -> element.getProductCategory().getName().equals("WorkTool"))
                .collect(Collectors.toList());
    }

    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            dataSource = new DatabaseManager().setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSource;
    }

    public static Product createCartObjectFromJson(String jsonElement) {
        return Util.createObjectFromJson(jsonElement);
    }

        public static byte[] hashPassword(String password) {
        Random random = new Random();

        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory;
        byte[] hash;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception){
            throw new RuntimeException(exception);
        }
        Base64.Encoder encoder = Base64.getEncoder();

        return hash;

    }
}
