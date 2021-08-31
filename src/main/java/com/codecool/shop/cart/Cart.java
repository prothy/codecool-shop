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
            content.put(product, getQuantity + 1);
            calculatePrice(product, true, content.get(product));
        } else {
            content.put(product, 1);
            calculatePrice(product, true, content.get(product));
        }

        System.out.println("Item has been added to the card");
    }

    public void removeProduct(Product product) {

        if (content.containsKey(product) && content.get(product) > 1) {
            int getQuantity = content.get(product);
            content.remove(product, getQuantity - 1);
            calculatePrice(product, false, content.get(product));
        } else {
            content.remove(product);
            calculatePrice(product, true, 0);
        }

        System.out.println("Item has been removed successfully!");
    }

    public BigDecimal calculatePrice(Product product, boolean checkCart, int getQuantity) {

        String[] splitPrice = product.getPrice().split(" ");
        BigDecimal getPrice = BigDecimal.valueOf(Long.parseLong(splitPrice[0]));

        if (checkCart) {
            return getPrice.multiply(BigDecimal.valueOf(getQuantity));
        } else if (getQuantity > 1) {
            return getPrice.divide(BigDecimal.valueOf(getQuantity));
        } else if (getQuantity == 0){
            return getPrice.subtract(getPrice);
        }
        else {
            return getPrice;
        }
    }
}
