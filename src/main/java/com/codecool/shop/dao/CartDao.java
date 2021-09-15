package com.codecool.shop.dao;

import com.codecool.shop.model.cart.CartItem;
import com.codecool.shop.service.CartService;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    void updateCart(CartService cart);
    List<CartItem> findAll(int userId) throws SQLException;
    void remove(int userId, int productId) throws SQLException;
    void remove(int userId) throws SQLException;
    void clearCart(int userId) throws SQLException;
    List<CartService> getAll();

}
