package com.codecool.shop.model;

import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.products.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private int id;
    private Cart cart;

    private OrderStatus orderStatus;

    public Order(int id, Cart cart) {
        this.id = id;
        this.cart = cart;
        this.orderStatus = OrderStatus.NEW;
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

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        return formatter.format(date);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}