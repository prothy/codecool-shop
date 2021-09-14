package com.codecool.shop.model.user;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.ProductCategory;

public abstract class User extends BaseModel {
    protected String email;
    protected String password;
    protected boolean isAdmin;

    public User(int id, String name) {
        super(id, name);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
