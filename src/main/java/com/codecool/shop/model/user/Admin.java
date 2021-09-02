package com.codecool.shop.model.user;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;

public class Admin extends User{
    private ProductCategoryDaoMem productCategoryDaoMem;
    private SupplierDaoMem supplierDaoMem;
    private ProductDaoMem productDaoMem;

    public Admin(int id, String name, ProductCategoryDaoMem productCategoryDaoMem, SupplierDaoMem supplierDaoMem, ProductDaoMem productDaoMem) {
        super(id, name);
        this.productCategoryDaoMem = productCategoryDaoMem;
        this.supplierDaoMem = supplierDaoMem;
        this.productDaoMem = productDaoMem;
    }

    public void changePriceOfProduct(int id) {
        // [TODO]: not implemented
    }

    public void addNewProduct(int id) {
        // [TODO]: not implemented
    }

    public void addNewProductCategory(int id) {
        // [TODO]: not implemented
    }
}
