package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.model.ProductCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

//    public ProductCategory getProductCategory(int categoryId){
//        return productCategoryDao.find(categoryId);
//    }
//
//    public List<Product> getProductsForCategory(int categoryId){
//        var category = productCategoryDao.find(categoryId);
//        return productDao.getBy(category);
//    }

    public List<Product> getProductsForCategory(String categoryName, List<Product> products) {
        List<Product> sortedProductList = new ArrayList<>();
        products.forEach(product -> {
            if (product.getProductCategory().getName().equals(categoryName)) {
                sortedProductList.add(product);
            }
        });

        return sortedProductList;
    }

    public List<Product> getProductsForSupplier(String supplierName, List<Product> products) {
        List<Product> sortedProductList = new ArrayList<>();
        products.forEach(product -> {
            if (product.getSupplier().getName().equals(supplierName)) {
                sortedProductList.add(product);
            }
        });

        return sortedProductList;
    }

    public List<List<Product>> createProductListFromJson() throws IOException {
        return productDao.createObjectsFromJson();
    }

    public Product createCartObjectFromJson(String jsonElement) throws IOException {
        return productDao.createObjectFromJson(jsonElement);
    }

}
