package com.codecool.shop.cart;

import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;

public class Cart {

    private HashMap<Product, Integer> content = new HashMap<>();
    private BigDecimal price;

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
            calculatePrice(product, false, 0);
        }

        System.out.println("Item has been removed successfully!");
    }

    public void calculatePrice(Product product, boolean checkCart, int getQuantity) {

        String[] splitPrice = product.getPrice().split(" ");
        BigDecimal getPrice = BigDecimal.valueOf(Long.parseLong(splitPrice[0]));

        if (checkCart) {
            setCalculatedPrice(getPrice.multiply(BigDecimal.valueOf(getQuantity)));
        } else if (!checkCart && getQuantity > 1) {
            setCalculatedPrice(getPrice.subtract(getPrice));
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
