package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.OrderValidition;
import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.products.OS;
import com.codecool.shop.model.products.Product;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.math.BigDecimal;

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


        OrderValidition valid = new OrderValidition("Kiss-Sándor", "aa@a.cc");
        System.out.println("validName: " + valid.isNameValid());
        System.out.println("validEmail: " + valid.isEmailValid());
        System.out.println("everythingValid: " + valid.everythingIsValid());

        Cart cart = new Cart();
        cart.addProduct(b);

        Order order = new Order(1, cart);
        System.out.println("date: " + order.getCurrentDate());
        System.out.println("content: " + order.getContent());
        System.out.println("id: " + order.getId());
        System.out.println("totalPrice: " + order.totalPrice());
        System.out.println("order: " + order.getOrderStatus());

    }
}
