package com.codecool.shop.model.user;

import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Cart;

public abstract class User extends BaseModel {
    protected String email;
    protected String password;
    protected boolean isAdmin;

    public abstract Cart getCart();
}
