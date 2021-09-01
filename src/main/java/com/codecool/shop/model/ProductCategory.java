package com.codecool.shop.model;

import com.codecool.shop.model.products.Product;

import java.util.List;

public class ProductCategory extends BaseModel {
    private List<Product> products;

    public ProductCategory(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}