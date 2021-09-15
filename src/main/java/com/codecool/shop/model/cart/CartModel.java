package com.codecool.shop.model.cart;

import com.codecool.shop.model.products.Product;

import java.math.BigDecimal;
import java.util.*;

public class CartModel {
    private int userId;
    private int productId;
    private List<CartItem> cart;

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

    public void addToCart(Product product) {
        this.addToCart(product, 1);
    }

    public void addToCart(Product product, int quantity) {
        boolean containsProduct = false;

        for (CartItem cartItem : cart) {
            if (cartItem.getProduct() == product) {
                int currentQuantity = cartItem.getQuantity();
                cartItem.setQuantity(currentQuantity + quantity);
                containsProduct = true;
            }
        }

        if (!containsProduct) {
            cart.add(new CartItem(product, quantity));
        }
    }

    public void removeAllFromCart(Product product) {

    }

    public void removeFromCart(Product product, int quantity) {

    }

    public List<CartItem> getCart() {
        return cart;
    }
}

