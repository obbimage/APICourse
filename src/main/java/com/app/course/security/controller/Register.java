package com.app.course.security.controller;


import com.app.course.config.Constants;
import com.app.course.models.User;
import com.app.course.security.user.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class Register {
    @Autowired
    UserSecurityService userSecurityService;

    @PostMapping("/educator")
    public ResponseEntity<?> registerEducator(@RequestBody User user) {
        user.setRole(Constants.ROLE_EDUCATOR);
        return userSecurityService.register(user);
    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setRole(Constants.ROLE_USER);
        return userSecurityService.register(user);
    }
}
