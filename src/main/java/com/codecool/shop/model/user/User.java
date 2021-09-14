package com.codecool.shop.model.user;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.ProductCategory;

public abstract class User extends BaseModel {
    private String email;
    private String password;
    private boolean isAdmin;

    public User(int id, String name, String email, String password, boolean isAdmin) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
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
