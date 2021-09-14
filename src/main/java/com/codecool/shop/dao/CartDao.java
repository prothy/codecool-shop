package com.codecool.shop.dao;

import com.codecool.shop.model.cart.Cart;

public interface CartDao {

    void add(Cart cart);
    Cart find(int id);
    void remove(int id);
}
