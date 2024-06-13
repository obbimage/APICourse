package com.app.course.controllers;

import com.app.course.models.Test;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService service;
    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAll(){
        return service.getAllTest();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getTestById(@PathVariable int id){
        return service.getTestById(id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertTest(@RequestBody Test test){
        return service.insertTest(test);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateTest(@RequestBody Test test, @PathVariable int id){
        return service.updateTest(test,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteTest(@PathVariable int id){
        return service.deleteById(id);
    }

}
