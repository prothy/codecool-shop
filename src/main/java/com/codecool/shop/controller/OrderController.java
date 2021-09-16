package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductsOrders;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.ProductsOrdersJdbc;
import com.codecool.shop.logger.AdminLog;
import com.codecool.shop.logger.ProperLogMessages;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "orderServlet", urlPatterns = {"/api/order/add","/api/order?userId&action"})
public class OrderController extends HttpServlet {

    ProperLogMessages logMessages = new ProperLogMessages();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = Util.getDataSource();
        OrderDao orderDao = new OrderDaoJdbc(dataSource);
        ProductsOrders productsOrders = new ProductsOrdersJdbc(dataSource);
        OrderService orderService = new OrderService(orderDao, productsOrders);

        Order newOrder = new Order(Integer.parseInt(req.getParameter("userId")));
        String jsonBody = "[" + Util.getJsonBodyOutOfFetch(req) + "]";

        System.out.println(jsonBody);
        List<Product> products = Util.createObjectsFromJson(jsonBody).stream().flatMap(Collection::stream).collect(Collectors.toList());

        if (req.getParameter("action").equals("add")) orderService.addNewOrderToDatabase(newOrder, products);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("Order added to orders"); //Use logger here

        logMessages.orderHasBeenAddedToTheOrders();
        AdminLog adminLog = new AdminLog(
                newOrder.getOrderStatus(), newOrder.getOrderId(), newOrder.getUserId(), newOrder.getCurrentDate(), newOrder.getCart());
        adminLog.adminLogDetailsSaveIntoFile();

        out.flush();
    }
}
