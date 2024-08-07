package com.app.course.security;

import com.app.course.models.User;

public class PayloadLogin {

    private User user;
    private String token;

    public PayloadLogin(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
