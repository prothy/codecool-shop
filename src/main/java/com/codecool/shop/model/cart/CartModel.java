package com.codecool.shop.model.cart;

import com.codecool.shop.model.products.Product;

import java.math.BigDecimal;
import java.util.*;

public class CartModel {
    private int userId;
    private int productId;

    public CartModel(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public CartModel() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

