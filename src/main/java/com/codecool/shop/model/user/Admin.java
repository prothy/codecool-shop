package com.codecool.shop.model.user;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;

public class Admin extends User{
    private ProductCategoryDaoMem productCategoryDaoMem;
    private SupplierDaoMem supplierDaoMem;
    private ProductDaoMem productDaoMem;

    public Admin(int id, String name, String email, String password, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.productCategoryDaoMem = new ProductCategoryDaoMem();
        this.supplierDaoMem = new SupplierDaoMem();
        this.productDaoMem = new ProductDaoMem();
    }

    public void changePriceOfProduct(int id) {
        // [TODO]: not implemented
    }

    public void changePriceOfProduct(int id) {
        // [TODO]: not implemented
    }

    public void addNewProductCategory(int id) {
        // [TODO]: not implemented
    }

}
