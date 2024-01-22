package com.app.course.controllers;

import com.app.course.models.Educator;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.EducatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/educator")
public class EducatorController {
    @Autowired
    EducatorService service;

    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllEducator(){
        return service.getAllLEducator();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getEducatorById(@PathVariable long id){
        return  service.getEducatorById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteEducatorById(@PathVariable long id){
        return  service.deleteEducatorById(id);
    }
}
