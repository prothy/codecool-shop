package com.codecool.shop.model.dao;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.service.CartService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;

public class CartDaoJdbcTest {
    static final Logger logger = LogManager.getLogger(CartDaoJdbc.class);
    DataSource dataSource;

    @BeforeEach
    void setup() {
        DatabaseManager databaseManager = new DatabaseManager();

        try {
            dataSource = databaseManager.setup();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    void updateCartThrowsNoException() {
        CartDaoJdbc cartDao = new CartDaoJdbc(dataSource);
        CartService cartService = new CartService();


    }
}
