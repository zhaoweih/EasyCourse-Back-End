package com.blade.demo.route.model;

import com.blade.demo.route.model.user.User;
import com.blade.jdbc.core.ActiveRecord;

public class Auth extends ActiveRecord {
    private String token;
    private User user;
    private String expiredDate;

    public Auth(String token, User user, String expiredDate) {
        this.token = token;
        this.user = user;
        this.expiredDate = expiredDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
