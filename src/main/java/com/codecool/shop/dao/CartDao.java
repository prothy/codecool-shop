package com.codecool.shop.dao;

import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.cart.CartModel;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    void updateCart(CartModel cart);
    List<CartModel> findAll(int userId) throws SQLException;
    void remove(int userId, int productId) throws SQLException;
    void remove(int userId) throws SQLException;
    void clearCart(int userId) throws SQLException;
    List<CartModel> getAll();

}
