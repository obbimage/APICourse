package com.app.course.controllers;

import com.app.course.config.Constants;
import com.app.course.models.User;
import com.app.course.repository.RepositoryObject;
import com.app.course.security.user.UserSecurityService;
import com.app.course.service.EducatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/educator")
public class EducatorController {
    @Autowired
    EducatorService educatorService;
    @Autowired
    UserSecurityService userSecurityService;

    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllEducator(){
        return educatorService.getAllLEducator();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getEducatorById(@PathVariable long id){
        return  educatorService.getEducatorById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteEducatorById(@PathVariable long id){
        return  educatorService.deleteEducatorById(id);
    }
}
