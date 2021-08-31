package com.codecool.shop.cart;

import com.codecool.shop.model.products.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Cart {

    private Map<Product, Integer> content = new HashMap<>();
    private BigDecimal price;

    public Cart() {

    }

    public void addProduct(Product product) {

        AtomicBoolean isKeyPresent = checkProductPresent(product);

        if (isKeyPresent.get()) {
            int getQuantity = 1; //TODO: check the right amount of product
            content.put(product, getQuantity + 1);
            calculatePrice(product, true, getQuantity);
        } else {
            content.put(product, 1);
            calculatePrice(product, true, 1);
        }

        System.out.println("Item has been added to the card");
    }

    private AtomicBoolean checkProductPresent(Product product) {

        AtomicBoolean isKeyPresent= new AtomicBoolean(false);

        Optional<Map.Entry<Product, Integer>> matchedEntry =
                content.entrySet().stream().
                                filter(element -> element.getKey().getName().equals(product.getName())).findAny();

        matchedEntry.ifPresent(value -> {
            isKeyPresent.set(true);
        });

        return isKeyPresent;

    }

    public void removeProduct(Product product) {

        AtomicBoolean isKeyPresent = checkProductPresent(product);

        if (isKeyPresent.get()) {
            int getQuantity = 1;
            content.remove(product, getQuantity - 1);
            calculatePrice(product, false, getQuantity);
        } else {
            content.remove(product);
            calculatePrice(product, false, 0);
        }

        System.out.println("Item has been removed successfully!");
    }

    public void calculatePrice(Product product, boolean checkCart, int getQuantity) {

        //TODO: save the previous item price

        String[] splitPrice = product.getPrice().split(" ");
        BigDecimal getPrice = new BigDecimal(splitPrice[0]);

        if (checkCart) {
            setCalculatedPrice(getPrice.multiply(BigDecimal.valueOf(getQuantity)));
        } else if (!checkCart && getQuantity > 1) {
            setCalculatedPrice(getPrice.multiply(BigDecimal.valueOf(getQuantity)).subtract(getPrice));
        } else if (!checkCart && getQuantity == 0){
            setCalculatedPrice(getPrice.subtract(getPrice));
        }
        else {
            setCalculatedPrice(getPrice);
        }
    }

    public void setCalculatedPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCalculatedPrice() {
        return this.price;
    }
}
