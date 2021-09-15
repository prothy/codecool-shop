package com.codecool.shop.model;

import com.codecool.shop.dao.DatabaseManager;

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

    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            dataSource = new DatabaseManager().setup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSource;
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
