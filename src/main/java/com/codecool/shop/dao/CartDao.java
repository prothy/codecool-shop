package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.cart.Cart;

import java.util.List;

public interface CartDao {
    void add(Cart cart);
    List<Cart> findAll(int userId);
    void remove(int id);
    List<Cart> getAll();
}
