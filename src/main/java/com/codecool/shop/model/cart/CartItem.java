package com.codecool.shop.model.cart;

import com.codecool.shop.model.products.Product;

public class CartItem {
    private int quantity;
    private Product product;

    public CartItem(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
