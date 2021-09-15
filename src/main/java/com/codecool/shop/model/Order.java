package com.codecool.shop.model;

import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.products.Product;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private int orderId;
    private int userId;
    private Timestamp orderDate;
    private OrderStatus orderStatus;
    private Cart cart;

    public Order(int orderId, Cart cart) {
        this.orderId = orderId;
        this.cart = cart;
    }

    public Order(int userId) {
        this.userId = userId;
        this.orderDate = new Timestamp(System.currentTimeMillis());
        this.orderStatus = OrderStatus.NEW;
    }

    public Order(int orderId, int userId, Timestamp orderDate, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public int getUserId() {
        return userId;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}