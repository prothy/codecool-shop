package com.codecool.shop.model.user;

import com.codecool.shop.model.BaseModel;

public abstract class User extends BaseModel {
    protected String email;
    protected String password;
    protected boolean isAdmin;

    public User(int id, String name, String email, String password, boolean isAdmin) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
