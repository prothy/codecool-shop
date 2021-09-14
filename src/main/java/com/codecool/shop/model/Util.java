package com.codecool.shop.model;

import com.codecool.shop.dao.DatabaseManager;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

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

}
