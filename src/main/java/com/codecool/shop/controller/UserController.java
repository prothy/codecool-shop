package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.user.Customer;
import com.codecool.shop.model.user.User;
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

@WebServlet(name = "userServlet", urlPatterns = "api/user-registration")
public class UserController extends HttpServlet {
    DataSource dataSource = Util.getDataSource();
    UserDao userDao = new UserDaoJdbc(dataSource);
    UserService userService = new UserService(userDao);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonBody = "[" + Util.getJsonBodyOutOfFetch(request) + "]";

        User user = userService.createUserObjectFromJson(jsonBody);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "New user created");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonObject);


    }
}
