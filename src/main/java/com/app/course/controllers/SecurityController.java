package com.app.course.controllers;


import com.app.course.models.User;
import com.app.course.security.user.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {
    @Autowired
    UserSecurityService securityService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userRegister){
        return securityService.register(userRegister);
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }
    @GetMapping("/auth")
    public String auth(){
        return "auth";
    }
}
