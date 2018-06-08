package com.blade.demo.route.user;


import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;

@Table(value = "t_user")
public class Login  extends ActiveRecord {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
