package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductsOrders;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.products.Product;

import java.util.List;

public class OrderService {
    private OrderDao orderDao;
    private ProductsOrders productsOrders;

    public OrderService(OrderDao orderDao, ProductsOrders productsOrders) {
        this.orderDao = orderDao;
        this.productsOrders = productsOrders;
    }

    public void addNewOrderToDatabase(Order order, List<Product> products) {
        orderDao.add(order);
        products.forEach(product -> productsOrders.add(1 ,product.getId()));
    }
}
