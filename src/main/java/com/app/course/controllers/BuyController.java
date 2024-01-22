package com.app.course.controllers;

import com.app.course.models.Buy;
import com.app.course.models.keys.StudentCourseKey;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buy")
public class BuyController {
    @Autowired
    BuyService service;

    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllBuy() {
        return service.getAllBuy();
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<RepositoryObject> getBuyById(@PathVariable long studentId,@PathVariable long courseId) {
        return service.getBuyById(studentId,courseId);
    }

    @PostMapping("student/{studentId}/course/{courseId}")
    public ResponseEntity<RepositoryObject> insertBuy(@RequestBody Buy buy,@PathVariable long studentId,@PathVariable long courseId) {
        return service.insertBuy(buy,studentId,courseId);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<RepositoryObject> deleteBuyId(@PathVariable long studentId, @PathVariable long courseId) {
        return service.deleteBuyById(studentId,courseId);
    }
}
