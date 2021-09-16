
package com.codecool.shop.model.cart;

import com.codecool.shop.logger.ProperLogMessages;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.service.CartService;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;

public class Cart {
    /**
     * content hash map has the following format:
     * {
     * key = "{String: product name}"
     * value = HashMap: {
     * key = Product: product
     * value = int: ?
     * }
     * }
     */
    private Map<String, HashMap<Product, Integer>> content = new HashMap<>();
    private Map<String, Integer> quantity = new HashMap<>();
    private Map<String, BigDecimal> sumEachItem = new HashMap<>();
    private Currency currency;
    private int totalNumberOfProducts = 0;
    private final ProperLogMessages properLogMessages = new ProperLogMessages();

    // block of initializations for database
    private final CartService cartService = new CartService();
    private final DataSource dataSource = Util.getDataSource();
    private final CartDaoJdbc cartDao = new CartDaoJdbc(dataSource);

    public Cart() {
    }

    public void addProduct(Product product) {
        currency = product.getDefaultCurrency();
        totalNumberOfProducts++;

        // add product to cart, then update sql (it'll remove related entries, then add them all again)
        cartService.addToCart(product);
        cartDao.updateCart(cartService);

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
        properLogMessages.addProductToCartLog(product.getName());
    }

    public void removeProduct(Product product) {
        totalNumberOfProducts--;

        // remove from cart, then update
        cartService.removeFromCart(product);
        cartDao.updateCart(cartService);

        if (content.containsKey(product.getName())) {

            HashMap<Product, Integer> innerMap = content.get(product.getName());

            Product firstKey = (Product) innerMap.keySet().toArray()[0];
            int quantity = innerMap.get(firstKey) - 1;

            if (quantity == 0) {
                content.remove(product.getName());
                this.quantity.remove(product.getName());
            } else {
                innerMap.put(firstKey, quantity);
                content.put(product.getName(), innerMap);
                this.quantity.put(product.getName(), quantity);
            }
            setQuantity(this.quantity);

            calculatePriceAfterRemoveItem(product, quantity);
        }
        properLogMessages.removeProductFromCartLog(product.getName());
    }

    private void calculatePriceAfterAddItem(Product product, int getQuantity) {

        if (product.getPrice() != null) {
            String[] splitPrice = product.getPrice().split(" ");
            BigDecimal getPrice = new BigDecimal(splitPrice[0]);

            BigDecimal getNewPrice = getPrice.multiply(BigDecimal.valueOf(getQuantity));
            setSumPrice(product.getName(), getNewPrice);
        }
    }

    private void calculatePriceAfterRemoveItem(Product product, int quantity) {

        if (product.getPrice() != null) {
            String[] splitPrice = product.getPrice().split(" ");
            BigDecimal getPrice = new BigDecimal(splitPrice[0]);

            BigDecimal getNewPrice = getPrice.multiply(BigDecimal.valueOf(quantity));
            sumEachItem.put(product.getName(), getNewPrice);
        }
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

    /**
     * refresh 'content'-field content
     */
    public void refreshContent() {
        cartService.refreshCart(cartDao);
        List<CartItem> cart = cartService.getCart();

        Map<String, HashMap<Product, Integer>> newContent = new HashMap<>();
        for (CartItem cartItem : cart) {
            HashMap<Product, Integer> productHashMap = new HashMap<>();
            productHashMap.put(cartItem.getProduct(), cartItem.getQuantity());

            newContent.put(
                    cartItem.getProduct().getName(),
                    productHashMap
            );
        }

        content = newContent;
    }

    public List<ProductDetail> convertProductDetail() {
        List<ProductDetail> productsDetails = new LinkedList<>();
        content.forEach((name, product) -> {
            for (Map.Entry<Product, Integer> details : product.entrySet()) {

                String getName = details.getKey().getName();
                String getPrice = details.getKey().getPrice();
                Integer quantity = this.quantity.get(getName);
                String sumPrice = this.sumEachItem.get(getName).toString() + " " + currency;

                productsDetails.add(new ProductDetail(getName, getPrice, quantity, sumPrice));
            }
        });

        return productsDetails;
    }

    public Map<String, HashMap<Product, Integer>> getContent() {
        return content;
    }

    public int getTotalNumberOfProducts() {
        return totalNumberOfProducts;
    }
}