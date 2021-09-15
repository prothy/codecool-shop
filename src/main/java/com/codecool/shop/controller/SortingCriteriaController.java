package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Util;
import com.codecool.shop.service.ProductCategoryService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.SupplierService;
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
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "productByCriteriaServlet", urlPatterns = {"/api/products?criteria=*"})
public class SortingCriteriaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource dataSource = Util.getDataSource();
        ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJDBC(dataSource);
        SupplierDao supplierDao = new SupplierDaoJDBC(dataSource);
        ProductCategoryService productCategoryService = new ProductCategoryService(productCategoryDataStore);
        SupplierService supplierService = new SupplierService(supplierDao);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();


        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (request.getParameter("criteria").equals("category")) out.print(gson.toJson(productCategoryService.getCategories()));
        if (request.getParameter("criteria").equals("supplier")) out.print(gson.toJson(supplierService.getAllSuppliers()));

        out.flush();

    }
}
