package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {
    void add(Order order);
    Order find();
    void remove(Order order);
    List<Order> getAll();
}
