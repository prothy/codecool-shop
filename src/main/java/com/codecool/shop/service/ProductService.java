package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.model.ProductCategory;

import java.io.IOException;
import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> createProductListFromJson() throws IOException {
        return productDao.createObjectsFromJson();
    }

    public List<ProductCategory> createCategoryListFromJson() {
        return null;
    }

    public List<Supplier> createSupplierListFromJson() {
        return null;
    }


}
