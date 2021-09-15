package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.cart.CartItem;
import com.codecool.shop.model.products.Product;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;

/**
 * This class is used to save the cart info that is then sent to the CartDao for use in SQL *
 */
public class CartService {
    private int userId;
    private int productId;
    private List<CartItem> cart;

    public CartService(int userId, int productId) {
        this(userId);
        this.productId = productId;
    }

    public CartService(int userId) {
        this();
        this.userId = userId;
    }

    public CartService() {
        cart = new ArrayList<>();
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

    public void removeFromCart(Product product) {
        this.removeFromCart(product, 1);
    }

    public void removeFromCart(Product product, int quantity) {
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            int newQuantity = cartItem.getQuantity() - quantity;

            if (cartItem.getProduct() == product) {
                if (newQuantity <= 0) {
                    cart.remove(i);
                    break;
                } else {
                    cartItem.setQuantity(newQuantity);
                }
            }
        }
    }

    public List<CartItem> getCart() {
        return cart;
    }
}

