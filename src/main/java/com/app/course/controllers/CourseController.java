package com.app.course.controllers;

import com.app.course.models.Course;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService service;
    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllCourse(){
        return service.getCourseAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getCourseById(@PathVariable long id){
        return service.getCourseById(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<RepositoryObject> getCourseByUserId(@PathVariable long id){
        return service.findCourseByUserId(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteCourseById(@PathVariable long id){
        return  service.deleteCourseById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateCourse(@RequestBody Course course, @PathVariable long id){
        return  service.updateCourseById(course,id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertCourse(@RequestBody Course course){
        return service.insertCourse(course);
    }
}
