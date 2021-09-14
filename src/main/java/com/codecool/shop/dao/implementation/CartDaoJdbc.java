package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.cart.Cart;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartDaoJdbc implements CartDao {
    private Connection connection;

    public CartDaoJdbc(DataSource ds) {
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            System.out.println("CartDao unable to connect to db. " + e.getMessage());
        }
    }

    @Override
    public void add(Cart cart) {
        int userId = cart.getUserId();
        int productId = cart.getProductId();

        try {
            PreparedStatement statement = connection.prepareStatement("""
                        INSERT INTO carts (user_id, product_id) 
                        VALUES(?, ?)
                        """);

            statement.setInt(1, userId);
            statement.setInt(2, productId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Cart find() {
        return null;
    }

    @Override
    public void remove(Cart cart) {

    }

    @Override
    public List<Cart> getAll() {
        return null;
    }
}
