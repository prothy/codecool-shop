package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.model.user.Admin;
import com.codecool.shop.model.user.Customer;
import com.codecool.shop.model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UserDaoMem implements UserDao {
    private List<User> data = new ArrayList<>();
    private static UserDaoMem instance = null;

    private UserDaoMem() {
    }

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        user.setId(data.size() + 1);
        data.add(user);
    }

    @Override
    public User find(int id) {
        return data.stream().filter(data -> data.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<List<User>> createObjectsFromJson() throws IOException {
        String file = "src/main/java/com/codecool/shop/resources/users.json";

        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder jsonText = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            jsonText.append(line);
            line = reader.readLine();
        }
        reader.close();
        List<List<User>> users = new ArrayList<>();
        users.add(getJsonOfCustomer(jsonText.toString()));
        users.add(getJsonOfAdmin(jsonText.toString()));
        return users;
    }

    private List<User> getJsonOfCustomer(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Customer> deserializer = (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();

            if (!jsonObject.get("isAdmin").getAsBoolean()) {
                return new Customer(
                        jsonObject.get("id").getAsInt(),
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("email").getAsString(),
                        jsonObject.get("password").getAsString(),
                        jsonObject.get("isAdmin").getAsBoolean(),
                        new HashSet<Order>(),
                        jsonObject.get("wallet").getAsBigDecimal(),
                        jsonObject.get("defaultCurrency").getAsString()
                );
            }
            return null;
        };
        gsonBuilder.registerTypeAdapter(Customer.class, deserializer);

        Gson customGson = gsonBuilder.create();
        Customer[] customers = customGson.fromJson(jsonString, Customer[].class);
        List<User> finalCustomers = new ArrayList<>(Arrays.asList(customers));
        finalCustomers.removeAll(Collections.singleton(null));

        return finalCustomers;
    }

    private List<User> getJsonOfAdmin(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Admin> deserializer = (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.get("isAdmin").getAsBoolean()) {
                return new Admin(
                        jsonObject.get("id").getAsInt(),
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("email").getAsString(),
                        jsonObject.get("password").getAsString(),
                        jsonObject.get("isAdmin").getAsBoolean()
                );
            }
            return null;
        };
        gsonBuilder.registerTypeAdapter(Admin.class, deserializer);

        Gson customGson = gsonBuilder.create();
        Admin[] admins = customGson.fromJson(jsonString, Admin[].class);
        List<User> finalAdmins = new ArrayList<>(Arrays.asList(admins));
        finalAdmins.removeAll(Collections.singleton(null));

        return finalAdmins;
    }
}
