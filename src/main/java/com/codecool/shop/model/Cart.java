package com.codecool.shop.model;

import com.codecool.shop.model.products.Product;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private final Map<String, HashMap<Product, Integer>> content = new HashMap<>();
    private final Map<String, Integer> quantity = new HashMap<>();
    private final Map<String, BigDecimal> sumPrice = new HashMap<>();

    public Cart() {
    }

    public void addProduct(Product product) {

        if (content.containsKey(product.getName())) {
            HashMap<Product, Integer> innerMap = content.get(product.getName());
            Product firstKey = (Product) innerMap.keySet().toArray()[0];

            int getQuantity = innerMap.get(firstKey) + 1;
            innerMap.put(firstKey, getQuantity);

            content.put(product.getName(), innerMap);
            this.quantity.put(product.getName(), getQuantity);
            calculatePriceAfterAddItem(product, getQuantity);
        } else {
            HashMap<Product, Integer> newContent = new HashMap<>();
            newContent.put(product, 1);

            content.put(product.getName(), newContent);
            this.quantity.put(product.getName(), 1);
            calculatePriceAfterAddItem(product, 1);
        }

        System.out.println("Item has been added to the cart!");
    }

    public void removeProduct(Product product) {

        if (content.containsKey(product.getName())) {

            HashMap<Product, Integer> innerMap = content.get(product.getName());

            Product firstKey = (Product) innerMap.keySet().toArray()[0];
            int quantity = innerMap.get(firstKey) - 1;

            if (quantity == 0) {
                content.remove(product.getName());
                this.quantity.remove(product.getName());
            }
            else {

                innerMap.put(firstKey, quantity);
                content.put(product.getName(), innerMap);
                this.quantity.put(product.getName(), quantity);

            }

            calculatePriceAfterRemoveItem(product, quantity);
        }

        System.out.println("Item has been removed successfully!");
    }

    private void calculatePriceAfterAddItem(Product product, int getQuantity) {

        String[] splitPrice = product.getPrice().split(" ");
        BigDecimal getPrice = new BigDecimal(splitPrice[0]);

        {
            BigDecimal getNewPrice = getPrice.multiply(BigDecimal.valueOf(getQuantity));
            setSumPrice(product.getName(), getNewPrice);
        }
    }

    private void calculatePriceAfterRemoveItem(Product product, int quantity) {

        String[] splitPrice = product.getPrice().split(" ");
        BigDecimal getPrice = new BigDecimal(splitPrice[0]);

        BigDecimal getNewPrice = getPrice.multiply(BigDecimal.valueOf(quantity));
        sumPrice.put(product.getName(), getNewPrice);
    }

    private void setSumPrice(String productName, BigDecimal price) {
        sumPrice.put(productName, price);
    }

    public BigDecimal getSumPrice() {

        List<BigDecimal> addBigDecimal = new ArrayList<BigDecimal>();

        for (Map.Entry<String, BigDecimal> prices :
                this.sumPrice.entrySet()) {

            BigDecimal totalPrice = new BigDecimal(String.valueOf(prices.getValue()));
            addBigDecimal.add(totalPrice);

        }

        return addBigDecimal.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

