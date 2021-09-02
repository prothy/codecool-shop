package com.codecool.shop.model.cart;

import com.codecool.shop.model.products.Product;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private final Map<String, HashMap<Product, Integer>> content = new HashMap<>();
    private Map<String, Integer> quantity = new HashMap<>();
    private Map<String, BigDecimal> sumEachItem = new HashMap<>();
    private Currency currency;

    public Cart() {
    }

    public void addProduct(Product product) {
        currency = product.getDefaultCurrency();

        if (content.containsKey(product.getName())) {
            HashMap<Product, Integer> innerMap = content.get(product.getName());
            Product firstKey = (Product) innerMap.keySet().toArray()[0];

            int getQuantity = innerMap.get(firstKey) + 1;
            innerMap.put(firstKey, getQuantity);

            content.put(product.getName(), innerMap);
            this.quantity.put(product.getName(), getQuantity);
            setQuantity(this.quantity);
            calculatePriceAfterAddItem(product, getQuantity);
        } else {
            HashMap<Product, Integer> newContent = new HashMap<>();
            newContent.put(product, 1);

            content.put(product.getName(), newContent);
            this.quantity.put(product.getName(), 1);
            setQuantity(this.quantity);
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
                setQuantity(this.quantity);
            } else {
                innerMap.put(firstKey, quantity);
                content.put(product.getName(), innerMap);
                this.quantity.put(product.getName(), quantity);
                setQuantity(this.quantity);
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
        sumEachItem.put(product.getName(), getNewPrice);
    }

    private void setSumPrice(String productName, BigDecimal price) {
        sumEachItem.put(productName, price);
    }

    public Map<String, Integer> getQuantity() {
        return quantity;
    }

    public String getSumPrice() {

        List<BigDecimal> addBigDecimal = new ArrayList<BigDecimal>();

        for (Map.Entry<String, BigDecimal> prices :
                this.sumEachItem.entrySet()) {

            BigDecimal totalPrice = new BigDecimal(String.valueOf(prices.getValue()));
            addBigDecimal.add(totalPrice);

        }

        return addBigDecimal.stream().reduce(BigDecimal.ZERO, BigDecimal::add) + " " + currency;
    }

    public Map<String, BigDecimal> getSumEachItem() {
        return sumEachItem;
    }

    public void setQuantity(Map<String, Integer> quantity) {
        this.quantity = quantity;
    }

    public List<ProductDetail> convertProductDetail() {

        List<ProductDetail> productsDetails = new LinkedList<>();
        content.forEach((name, product) -> {
            for (Map.Entry<Product, Integer> details: product.entrySet()) {

                String getName = details.getKey().getName();
                String getPrice = details.getKey().getPrice();
                Integer quantity = this.quantity.get(getName);
                String sumPrice= this.sumEachItem.get(getName).toString() + " " + currency;

                productsDetails.add(new ProductDetail(getName, getPrice, quantity, sumPrice));
            }
        });

        return productsDetails;
    }


}

