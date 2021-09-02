package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.OrderValidation;
import com.codecool.shop.model.cart.Cart;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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


        Cart cart = new Cart();
        // cart.addProduct(b);

        Order order = new Order(1, cart);
        System.out.println("date: " + order.getCurrentDate());
        System.out.println("content: " + order.getContent());
        System.out.println("id: " + order.getId());
        System.out.println("totalPrice: " + order.totalPrice());
        System.out.println("order: " + order.getOrderStatus());

    }
}
