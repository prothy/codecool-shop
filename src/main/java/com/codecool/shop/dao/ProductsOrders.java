package com.codecool.shop.dao;

import com.codecool.shop.model.products.Product;

import java.util.List;

public interface ProductsOrders {
    void add(int orderId, int productId);
    List<Product> find(int orderId);
}
