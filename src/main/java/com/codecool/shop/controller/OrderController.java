package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Util;
import com.codecool.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "orderServlet", urlPatterns = {"/api/order","/api/order?userId&action"})
public class OrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = Util.getDataSource();
        OrderDao orderDao = new OrderDaoJdbc(dataSource);
        OrderService orderService = new OrderService(orderDao);

        Order newOrder = new Order(Integer.parseInt(req.getParameter("userId")));
        if (req.getParameter("action").equals("add")) orderService.addNewOrderToDatabase(newOrder);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("Order added to orders"); //Use logger here
        out.flush();
    }
}
