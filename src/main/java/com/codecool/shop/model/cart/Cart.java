package com.codecool.shop.model.cart;

import com.codecool.shop.model.products.Product;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
    private int userId;
    private int productId;

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

