package com.codecool.shop.dao;


import com.codecool.shop.model.user.User;

import java.io.IOException;
import java.util.List;

public interface UserDao {

    void add(User user);
    User find(int id);
    void remove(int id);

    List<List<User>> createObjectsFromJson() throws IOException;

}
