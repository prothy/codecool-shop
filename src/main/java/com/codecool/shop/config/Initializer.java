package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.logger.AdminLog;
import com.codecool.shop.logger.ProperLogMessages;
import com.codecool.shop.model.OrderStatus;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.cart.CartModel;
import com.codecool.shop.model.products.Cloud;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier(1, "me");
        supplierDataStore.add(amazon);
        CartModel cart = new CartModel();

        Cart testCart = new Cart();
        Cloud cloud = new Cloud(0, "Google Drive", "", new ProductCategory(0, "test"),
                new Supplier(0, "test"), "", new BigDecimal(0.1), new BigDecimal(0.1), new BigDecimal(0.1));
        testCart.addProduct(cloud);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        AdminLog logTest = new AdminLog(OrderStatus.NEW, 1, 0, timestamp, testCart);
        try {
            logTest.adminLogDetailsSaveIntoFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
