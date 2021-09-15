package com.codecool.shop.dao;


import com.codecool.shop.model.user.User;


public interface UserDao {

    void add(User user);
    User find(int id);
    User find(String email);
    void remove(int id);

    User createObjectFromJson(String jsonElement);


}
