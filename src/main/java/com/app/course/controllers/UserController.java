package com.app.course.controllers;

import com.app.course.DTO.User.UserUpdateRequest;
import com.app.course.models.User;
import com.app.course.payload.request.ChangePasswordRequest;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.UserRepository;
import com.app.course.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    UserRepository userRepository;
    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllUser(){
        return service.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getUserById(@PathVariable long id){
        return service.getUserById(id);
    }

    // lấy danh sách educator
    @GetMapping("/educator")
    public ResponseEntity<?> getAllEducator(){
        return service.getAllEducatorToUser();
    }
    @GetMapping("/educator/page/{pageNumber}/{pageSize}")
    public ResponseEntity<?> getAllEducator(@PathVariable int pageNumber, @PathVariable int pageSize){
        return service.getAllEducatorToUser(pageNumber,pageSize);
    }
    @GetMapping("/educator/search/{search}/page/{pageNumber}/{pageSize}")
    public ResponseEntity<?> getAllEducator(@PathVariable String search, @PathVariable int pageNumber, @PathVariable int pageSize){
        return service.findEducators(search,pageSize,pageSize);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertUser(@RequestBody User user){
        return service.insertUser(user);
    }

    @PutMapping("")
    public ResponseEntity<RepositoryObject> putUser(@RequestBody User user){
        return Response.resultOk(userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteUserById(@PathVariable long id) {
        return service.deleteUserById(id);
    }

    @PutMapping("/updateInfo/{id}")
    public ResponseEntity<RepositoryObject> updateInfoUser(@RequestBody UserUpdateRequest userRequest, @PathVariable long id){
        return service.updateInfoUser(userRequest, id);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<RepositoryObject> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, HttpServletRequest httpServletRequest){
             return service.changePassword(changePasswordRequest.getId(),changePasswordRequest.getOldPassword(),changePasswordRequest.getNewPassword(), httpServletRequest);
    }

    @PostMapping("/updateAvatar/{id}")
    public ResponseEntity<?> updateAvatar(@PathVariable long id, @RequestParam MultipartFile file){
        return service.updateAvatarUser(id,file);
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
