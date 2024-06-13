package com.app.course.security.controller;


import com.app.course.config.Constants;
import com.app.course.models.User;
import com.app.course.security.user.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class Register {
    @Autowired
    UserSecurityService userSecurityService;


    @PostMapping("/educator")
    public ResponseEntity<?> registerEducator(@RequestBody User user) {
        return userSecurityService.registerEducator(user);
    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userSecurityService.registerUser(user);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmAccount(@RequestParam String token){
        return userSecurityService.confirmUser(token);

    }


}
