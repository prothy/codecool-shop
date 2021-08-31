package com.codecool.shop.cart;

import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;

public class Cart {

    private HashMap<Product, Integer> content = new HashMap<>();

    Cart() {

    }

    public void addProduct(Product product) {

        if (content.containsKey(product)) {
            int getQuantity = content.get(product);
            content.put(product, getQuantity+1);
        }
        else {
            content.put(product, 1);
        }

        System.out.println("Item has been added to the card");
    }

    public void removeProduct(Product product) {

        if (content.containsKey(product) && content.get(product) > 1) {
            int getQuantity = content.get(product);
            content.remove(product, getQuantity-1);
        }
        else {
            content.remove(product);
        }

        System.out.println("Item has been removed successfully!");
    }

    public BigDecimal calculatePrice() {
        return null;
    }
}
