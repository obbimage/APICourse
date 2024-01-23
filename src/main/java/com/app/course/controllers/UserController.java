package com.app.course.controllers;

import com.app.course.models.User;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllUser(){
        return service.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getUserById(@PathVariable long id){
        return service.getUserById(id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertUser(@RequestBody User user){
        return service.insertUser(user);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteUserById(@PathVariable long id) {
        return service.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateInfoUser(@RequestBody User user, @PathVariable long id){
        return service.updateIfoUser(user, id);
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<RepositoryObject> updatePassUser(@RequestBody User user, @PathVariable long id){
//        return service.updatePassUser(user, id);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<RepositoryObject> updateAvatarUser(@RequestBody User user, @PathVariable long id){
//        return service.updateAvatarUser(user, id);
//    }
}
