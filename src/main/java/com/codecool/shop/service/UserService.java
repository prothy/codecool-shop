package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Util;
import com.codecool.shop.model.user.Admin;
import com.codecool.shop.model.user.Customer;
import com.codecool.shop.model.user.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class UserService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /*public User getUserById(int id) {
        return userDao.find(id);
    }*/

//    public User getUserById(int id) throws IOException {
//        var userList = userDao.createObjectsFromJson();
//        User sad = userList.get(0).get(0);
//        return userList.stream()
//                .flatMap(Collection::parallelStream)
//                .filter(user -> user.getId() == id)
//                .findFirst().orElse(null);
//    }

    public <T extends User> T convertUserObject(User user, Class<T> type) {
        return type.cast(user);
    }

    public User createUserObjectFromJson(String jsonElement) {
        // Password hashing not working
//        JsonObject jsonObject = new Gson().fromJson(jsonElement, JsonObject.class);
//        byte[] hashPassword = Util.hashPassword(jsonObject.get("password").getAsString());
//        jsonObject.addProperty("password", Arrays.toString(hashPassword));


        return userDao.createObjectFromJson(jsonElement);
    }

    public User findUser(String jsonElement) {
        JsonObject jsonObject = new Gson().fromJson(jsonElement, JsonObject.class);

        return userDao.find(jsonObject.get("email").getAsString());
    }
}
