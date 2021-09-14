package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.user.Admin;
import com.codecool.shop.model.user.Customer;
import com.codecool.shop.model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        String jsonText = Util.readDataFromFile(file);

        List<List<User>> users = new ArrayList<>();
        users.add(getJsonOfCustomer(jsonText));
        users.add(getJsonOfAdmin(jsonText));
        return users;
    }

    private List<User> getJsonOfCustomer(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson customGson = gsonBuilder.create();
        Customer[] customers = customGson.fromJson(jsonString, Customer[].class);
        List<User> finalCustomers = new ArrayList<>(Arrays.asList(customers));

        return finalCustomers.stream()
                .filter(element -> !element.isAdmin())
                .collect(Collectors.toList());
    }

    private List<User> getJsonOfAdmin(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson customGson = gsonBuilder.create();
        Admin[] admins = customGson.fromJson(jsonString, Admin[].class);
        List<User> finalAdmins = new ArrayList<>(Arrays.asList(admins));

        return finalAdmins.stream()
                .filter(User::isAdmin)
                .collect(Collectors.toList());
    }
}
