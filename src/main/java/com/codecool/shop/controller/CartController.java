package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.cart.Cart;
import com.codecool.shop.model.products.OS;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.model.products.SubscriptionProduct;
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
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

@WebServlet(name = "cartServlet",urlPatterns = {"/api/cart", "/api/cart?action=*"})
public class CartController extends HttpServlet {
    DataSource dataSource = Util.getDataSource();
    ProductDao productDataStore = new ProductDaoJDBC(dataSource);
    ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJDBC(dataSource);
    SupplierDao supplierDao = new SupplierDaoJDBC(dataSource);
    ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDao);
    UserDao userDataStore = UserDaoMem.getInstance();
    UserService userService = new UserService(userDataStore);

    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    Customer user = new Customer();
    Cart userCart = user.getCart();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonBody = "[" + Util.getJsonBodyOutOfFetch(request) + "]";

        System.out.println(jsonBody);
        Product jsonObject = productService.createCartObjectFromJson(jsonBody);
        if (request.getParameter("action").equals("add")) {
            BigDecimal newPrice;
            if (jsonObject instanceof SubscriptionProduct) {
                newPrice = ((SubscriptionProduct) jsonObject).getYearlyPrice();
                jsonObject.setPrice(newPrice, "USD");
            }

            jsonObject.setDefaultCurrency(Currency.getInstance("USD"));
            userCart.addProduct(jsonObject);
        } else if (request.getParameter("action").equals("remove")) {
            BigDecimal newPrice;
            if (jsonObject instanceof SubscriptionProduct) {
                newPrice = ((SubscriptionProduct) jsonObject).getYearlyPrice();
                jsonObject.setPrice(newPrice, "USD");
            }

            jsonObject.setDefaultCurrency(Currency.getInstance("USD"));
            userCart.removeProduct(jsonObject);
        }
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonObject productOfCart = new JsonObject();
        productOfCart.add("products", gson.toJsonTree(userCart.convertProductDetail()));
        productOfCart.add("totalPrice", gson.toJsonTree(userCart.getSumPrice()));
        productOfCart.add("itemNumber", gson.toJsonTree(userCart.getTotalNumberOfProducts()));


        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(productOfCart);
        out.flush();
        }

}
