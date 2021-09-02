package com.codecool.shop.config;

import com.codecool.shop.cart.Cart;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
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

//        setting up a new product category
        ProductCategory tablet = new ProductCategory(1, "tablet");
        productCategoryDataStore.add(tablet);

        //setting up products and printing it
//        productDataStore.add();
        Product a = new OS(1, "test", "des", new ProductCategory(1, "tablet"), new Supplier(1, "me"), "https://res.cloudinary.com/teepublic/image/private/s--RGjbI5F---/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1565703885/production/designs/5596155_2.jpg", BigDecimal.valueOf(64.4), 64);
        try {
            System.out.println(productDataStore.createObjectsFromJson());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product b = new OS(1, "test", "des", new ProductCategory(1, "tablet"), new Supplier(1, "me"), "https://res.cloudinary.com/teepublic/image/private/s--RGjbI5F---/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1565703885/production/designs/5596155_2.jpg", BigDecimal.valueOf(64.4), 64);
        Product c = new OS(3, "test2", "des", new ProductCategory(1, "tablet"), new Supplier(1, "me"), "https://res.cloudinary.com/teepublic/image/private/s--RGjbI5F---/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1565703885/production/designs/5596155_2.jpg", BigDecimal.valueOf(64.4), 64);
        Product d = new OS(4, "test22", "des", new ProductCategory(1, "tablet"), new Supplier(1, "me"), "https://res.cloudinary.com/teepublic/image/private/s--RGjbI5F---/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1565703885/production/designs/5596155_2.jpg", BigDecimal.valueOf(64.4), 64);
        Product e = new OS(5, "test", "des", new ProductCategory(1, "tablet"), new Supplier(1, "me"), "https://res.cloudinary.com/teepublic/image/private/s--RGjbI5F---/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1565703885/production/designs/5596155_2.jpg", BigDecimal.valueOf(64.4), 64);

        Cart test = new Cart();
        test.addProduct(a);
        test.addProduct(b);
        test.addProduct(c);
        test.addProduct(d);
        test.addProduct(e);
        System.out.println(test.getSumPrice());
        System.out.println(test.convertProductDetail());

    }
}
