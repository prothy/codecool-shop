package com.codecool.shop.cart;

import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;

public class Cart {

    private HashMap<Product, Integer> content = new HashMap<>();

    Cart() {

    }

    public void addProduct(Product product) {
        content.put(product, 1);
        System.out.println("Item has been added to the card");
    }

    public void removeProduct(Product product) {
        content.remove(product);
        System.out.println("Item has been removed successfully!");
    }

    public BigDecimal calculatePrice() {
        return null;
    }
}
