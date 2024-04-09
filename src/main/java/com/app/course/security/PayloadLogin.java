package com.app.course.security;

import com.app.course.models.User;
import com.app.course.security.user.UserSecurity;

public class Payload {

    private User user;
    private String token;

    public Payload(User user, String token) {
        this.user = user;
        this.token = token;
    }
    public Payload(UserSecurity userSecurity, String token) {
        this.user = userSecurity.;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
