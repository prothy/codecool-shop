package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.service.ProductService;
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
import java.util.List;

@WebServlet(name = "productServlet",urlPatterns = {"/api.noIDEaSHop", "/api.noIDEaSHop?category=*", "/api.noIDEaSHop?supplier=*"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource dataSource = Util.getDataSource();
        ProductDao productDataStore = new ProductDaoJDBC(dataSource);
        ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJDBC(dataSource);
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        List<Product> productList = productService.getProducts();

        if (request.getParameter("category") != null) {
            productList = productService.getProductsForCategory(Integer.parseInt(request.getParameter("category")));
        } else if (request.getParameter("supplier") != null) {
            productList = productService.getProductsForSupplier(Integer.parseInt(request.getParameter("supplier")));
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(gson.toJson(productList));
        out.flush();
    }

}
