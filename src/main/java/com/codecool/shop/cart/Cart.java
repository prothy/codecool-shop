package com.codecool.shop.cart;

import com.codecool.shop.model.products.Product;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private final Map<String, HashMap<Product, Integer>> content = new HashMap<>();
    private Map<String, Integer> quantity = new HashMap<>();
    private Map<String, BigDecimal> sumPrice = new HashMap<>();

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

//    public void addProduct(Product product) {
//
//        AtomicBoolean isKeyPresent = checkProductPresent(product);
//        boolean checkPresent = checkPresent(product);
//
//        if (checkPresent) {
//            content.put(product, getQuantity.get(product.getName()) + 1);
//            calculatePriceAfterAddItem(product, getQuantity.get(product.getName()) + 1);
//        } else {
//            content.put(product, 1);
//            calculatePriceAfterAddItem(product, 1);
//        }

    //System.out.println(product.getName()+ " " +getQuantity.get(product.getName()));
//
//        System.out.println("Item has been added to the card");
//    }


//    private AtomicBoolean checkProductPresent(Product product) {
//
//        AtomicBoolean isKeyPresent = new AtomicBoolean(false);
//
//        Optional<Map.Entry<Product, Integer>> matchedEntry =
//                content.entrySet().stream().
//                        filter(element -> element.getKey().getName().equals(product.getName())).findAny();
//
//        matchedEntry.ifPresent(value -> {
//
//            for (Map.Entry<String, Integer> prices :
//                    this.getQuantity.entrySet()) {
//
//                if (getQuantity.containsKey(value.getKey().getName())) {
//                    getQuantity.put(value.getKey().getName(), value.getValue() + 1);
//                    isKeyPresent.set(true);
//                } else {
//                    getQuantity.put(value.getKey().getName(), 1);
//                    isKeyPresent.set(false);
//                }
//            }
//
//        });
//
//        return isKeyPresent;
//
//    }

    public void removeProduct(Product product) {

        if (content.containsKey(product.getName())) {

            HashMap<Product, Integer> innerMap = content.get(product.getName());

            Product firstKey = (Product) innerMap.keySet().toArray()[0];
            int quantity = innerMap.get(firstKey) + 1;
            innerMap.remove(firstKey, quantity);

            content.remove(product.getName(), innerMap);
            System.out.println(this.quantity.values());
            this.quantity.put(product.getName(), this.quantity.get(product.getName()) - 1);
            System.out.println(this.quantity.get(product.getName()));
            calculatePriceAfterRemoveItem(product, this.quantity.get(product.getName()) - 1);

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

    private void calculatePriceAfterRemoveItem(Product product, int getQuantity) {

        String[] splitPrice = product.getPrice().split(" ");
        BigDecimal getPrice = new BigDecimal(splitPrice[0]);
        BigDecimal newPrice = getPrice.multiply(BigDecimal.valueOf(getQuantity));

        BigDecimal getNewPrice = newPrice.subtract(getPrice);
        sumPrice.remove(product.getName(), getPrice);
        setSumPrice(product.getName(), getNewPrice);
    }

    private void setSumPrice(String productName, BigDecimal price) {
        sumPrice.put(productName, price);
    }

    public BigDecimal getAllPrice() {

        List<BigDecimal> addBigDecimal = new ArrayList<BigDecimal>();

        for (Map.Entry<String, BigDecimal> prices :
                this.sumPrice.entrySet()) {

            BigDecimal totalPrice = new BigDecimal(String.valueOf(prices.getValue()));
            addBigDecimal.add(totalPrice);

        }

        return addBigDecimal.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

