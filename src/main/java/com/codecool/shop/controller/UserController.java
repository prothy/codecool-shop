package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.logger.ProperLogMessages;
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

@WebServlet(name = "userServlet", urlPatterns = {"api/user/registration", "api/user/login"})
public class UserController extends HttpServlet {
    DataSource dataSource = Util.getDataSource();
    UserDao userDao = new UserDaoJdbc(dataSource);
    UserService userService = new UserService(userDao);
    ProperLogMessages logMessages = new ProperLogMessages();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonBody = Util.getJsonBodyOutOfFetch(request);
        String url = request.getServletPath();
        if (url.equals("/api/user/registration")) {
            userRegistration(request, response, jsonBody);
        } else if (url.equals("/api/user/login")) {
            userLogin(request, response, jsonBody);
        }

    }

    private void userLogin(HttpServletRequest request, HttpServletResponse response, String jsonBody ) throws IOException {
//        User user = userService.findUser(jsonBody);

        JsonObject jsonObject = new JsonObject();
//        if (user.equals(null)) {
//            jsonObject.addProperty("message", "User is not found");
//            logMessages.userNotFoundInTheDatabase(user.getName());
//        } else {
//            jsonObject.addProperty("message", "User is logged in");
//            logMessages.userHasBeenLoggedIntoTheWebShop(user.getName());
//        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonObject);

    }

    private void userRegistration(HttpServletRequest request, HttpServletResponse response, String jsonBody ) throws IOException {
        User user = userService.createUserObjectFromJson(jsonBody);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "New user created");
        logMessages.userHasBeenRegisteredToTheWebShop(user.getName());
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonObject);
    }
}
