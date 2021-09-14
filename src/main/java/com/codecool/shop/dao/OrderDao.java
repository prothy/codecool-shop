package com.codecool.shop.dao;

import com.codecool.shop.model.OrderModel;

import java.util.List;

public interface OrderDao {
    void add(OrderModel order);
    OrderModel find(OrderModel order);
    void remove(int id);
    List<OrderModel> getAll();
}
