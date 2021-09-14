package com.codecool.shop.controller;

import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ProductsDaoJDBC;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.model.user.User;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "productServlet",urlPatterns = {"/api.noIDEaSHop", "/api.noIDEaSHop?category=*", "/api.noIDEaSHop?supplier=*"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource dataSource = Util.getDataSource();
        ProductDao productDataStore = new ProductsDaoJDBC(dataSource);
        ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJDBC(dataSource);
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        List<Product> productList = productService.getProducts();

//        if (request.getParameter("category") != null) {
//            productList = productService.getProductsForCategory(request.getParameter("category"), productList);
//        } else if (request.getParameter("supplier") != null) {
//            productList = productService.getProductsForSupplier(request.getParameter("supplier"), productList);
//        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(productList));
        out.flush();
    }

}
