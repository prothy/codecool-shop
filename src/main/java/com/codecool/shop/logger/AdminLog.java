package com.codecool.shop.logger;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderStatus;
import com.codecool.shop.model.cart.Cart;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class AdminLog {

    private final Logger loggerForOrder = Logger.getLogger(Order.class.getName());
    private ProperLogMessages logMessages = new ProperLogMessages();

    private OrderStatus orderStatus;
    private int orderId;
    private int userId;
    private String orderDate;

    private boolean orderValidation;
    private Cart cart = new Cart();

    //Use this:
    public AdminLog(OrderStatus orderStatus, int orderId, int userId, String orderDate, Cart cart) {
        this.orderStatus = orderStatus;
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.cart = cart;
    }

    public void adminLogDetailsSaveIntoFile() throws IOException {

        //Save as a log file
        String pathLocationWithLog = "./src/main/logs/logsFile/orderID" + orderId + ".txt";

        FileAppender appender = new DailyRollingFileAppender(new PatternLayout(
                PatternLayout.DEFAULT_CONVERSION_PATTERN), pathLocationWithLog,
                "'.'yyyy-MM-dd");
        try {

            loggerForOrder.addAppender(appender);

            if (cart.getContent().size() != 0) {
                loggerForOrder.info("{" + userId + "}" + " userID is ready to order!" + " | Order status: " + orderStatus +
                        " | Cart size: " + cart.getContent().size());
            } else {
                loggerForOrder.info("The userID: " + userId + "is emptied their cart" + " | Order status: CANCELED");
            }

            logMessages.adminLogHasBeenCreated();

        } catch (SecurityException ignored) {
            logMessages.adminLogHasBeenCreatedWarning();
        }
        finally {
            appender.close();
            saveIntoJSON(pathLocationWithLog, orderId, orderDate);
        }
    }

    private void saveIntoJSON(String pathLocationForLog, int orderId, String orderDate) throws IOException {

        String filePathForJSON = "./src/main/logs/logsAsJSON/orderID" + orderId + ".json";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        JsonObject dataJSONLog = new JsonObject();
        dataJSONLog.addProperty("pathForTheLog", pathLocationForLog);
        dataJSONLog.addProperty("orderId", orderId);
        dataJSONLog.addProperty("orderDate", String.valueOf(orderDate));
        dataJSONLog.addProperty("addDate", formatter.format(date));

        FileWriter writer = null;

        try {
            Gson gson = new Gson();
            writer = new FileWriter(filePathForJSON,true);
            gson.toJson(dataJSONLog, writer);
            logMessages.saveAdminLogInformationIntoJSON();

        } catch (IOException ignored) {
            logMessages.saveAdminLogInformationIntoJSONWarning();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
