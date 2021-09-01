package com.codecool.shop.model;

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
}
