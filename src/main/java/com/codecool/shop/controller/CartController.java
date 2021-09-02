package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.products.OS;
import com.codecool.shop.model.user.Customer;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

@WebServlet(name = "cartServlet",urlPatterns = {"/api.cart", "/api.cart?action=*"})
public class CartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
        UserDao userDataStore = UserDaoMem.getInstance();
        UserService userService = new UserService(userDataStore);


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Customer user = new Customer();
        Cart userCart = user.getCart();
        userCart.addProduct(new OS(1, "test", "des", new ProductCategory(1, "tablet"), new Supplier(1, "me"), "https://res.cloudinary.com/teepublic/image/private/s--RGjbI5F---/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1565703885/production/designs/5596155_2.jpg", BigDecimal.valueOf(64.4), 64));
        userCart.addProduct(new OS(1, "test", "des", new ProductCategory(1, "tablet"), new Supplier(1, "me"), "https://res.cloudinary.com/teepublic/image/private/s--RGjbI5F---/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1565703885/production/designs/5596155_2.jpg", BigDecimal.valueOf(64.4), 64));
        userCart.addProduct(new OS(1, "tes2t", "des", new ProductCategory(1, "tablet"), new Supplier(1, "me"), "https://res.cloudinary.com/teepublic/image/private/s--RGjbI5F---/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1565703885/production/designs/5596155_2.jpg", BigDecimal.valueOf(64.4), 64));

        JsonObject productOfCart = new JsonObject();
        productOfCart.add("products", gson.toJsonTree(userCart.convertProductDetail()));
        productOfCart.add("totalPrice", gson.toJsonTree(userCart.getSumPrice()));


        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(productOfCart);
        out.flush();
        }

}
