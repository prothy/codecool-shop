package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

public class OrderService {
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void addNewOrderToDatabase(Order order) {
        orderDao.add(order);
    }
}
