package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.cart.CartModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public void add(CartModel cart) {
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
    public List<CartModel> findAll(int userId) {
        List<CartModel> cartContent = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM carts
                    WHERE user_id = ?
                    """);

            statement.setInt(1, userId);

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                cartContent.add(new CartModel(results.getInt(0), results.getInt(1)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cartContent;
    }

    @Override
    public void remove(int userId, int productId) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM carts
                    WHERE user_id = ? AND product_id = ?
                    """);

            statement.setInt(1, userId);
            statement.setInt(2, productId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM carts
                    WHERE user_id = ?
                    """);

            statement.setInt(1, userId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void clearCart(int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM carts
                    WHERE user_id = ?
                    """);

            statement.setInt(1, userId);

            statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<CartModel> getAll() {
        List<CartModel> cartContent = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT *
                    FROM carts
                    """);

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                cartContent.add(new CartModel(results.getInt(0), results.getInt(1)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cartContent;
    }
}
