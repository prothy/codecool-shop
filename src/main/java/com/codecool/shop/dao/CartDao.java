package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.cart.Cart;

import java.util.List;

public interface CartDao {
    void add(Cart cart);
    Cart find();
    void remove(Cart cart);
    List<Cart> getAll();
}
