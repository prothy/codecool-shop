package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.user.User;

import java.io.IOException;
import java.util.Collection;

public class UserService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /*public User getUserById(int id) {
        return userDao.find(id);
    }*/

    public User getUserById(int id) throws IOException {
        var userList = userDao.createObjectsFromJson();
        User sad = userList.get(0).get(0);
        return userList.stream()
                .flatMap(Collection::parallelStream)
                .filter(user -> user.getId() == id)
                .findFirst().orElse(null);
    }
}
