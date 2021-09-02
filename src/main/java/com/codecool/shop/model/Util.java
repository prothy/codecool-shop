package com.codecool.shop.model;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

}
