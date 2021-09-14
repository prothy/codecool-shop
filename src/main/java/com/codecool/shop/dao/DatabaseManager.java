package com.codecool.shop.dao;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.ds.PGSimpleDataSource;

public class DatabaseManager {

    public void setup() throws SQLException {
        Properties prop = new Properties();
        String filename = "src/main/java/com/codecool/shop/resources/connection.properties";

        try {
            FileInputStream input = new FileInputStream(filename);

            prop.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }

        DataSource dataSource = connect(prop);

    }

    private DataSource connect(Properties prop) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setUser(prop.getProperty("user"));
        dataSource.setPassword(prop.getProperty("password"));
        dataSource.setDatabaseName(prop.getProperty("database"));

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
