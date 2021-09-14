package com.codecool.shop.model;

import com.codecool.shop.model.cart.CartModel;

import java.sql.Timestamp;
import java.time.Instant;

public class OrderModel {
    private int orderId;
    private int userId;
    private Timestamp orderDate;
    private String orderStatus;

    public OrderModel(int orderId, int userId, Timestamp orderDate, String orderStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = Timestamp.from(Instant.now());
        this.orderStatus = orderStatus;
    }

    public OrderModel(int orderId, int userId, String orderStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderStatus = orderStatus;
    }

    public OrderModel(int i, CartModel cart) {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
