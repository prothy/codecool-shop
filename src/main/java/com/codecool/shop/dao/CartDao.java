package com.codecool.shop.dao;

import com.codecool.shop.model.cart.CartModel;

import java.util.List;

public interface CartDao {
    void add(CartModel cart);
    List<CartModel> findAll(int userId);
    void remove(int userId, int productId);
    void remove(int userId);
    void clearCart(int userId);
    List<CartModel> getAll();
}
