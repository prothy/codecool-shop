package com.codecool.shop.model;

import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.products.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private int id;
    private Cart cart;

    public Order(int id, Cart cart) {
        this.id = id;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public String totalPrice() {
        return cart.getSumPrice();
    }

    public Map<String, HashMap<Product, Integer>> getContent() {
        return cart.getContent();
    }

}
