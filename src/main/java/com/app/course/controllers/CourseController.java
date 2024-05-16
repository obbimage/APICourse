package com.app.course.controllers;

import com.app.course.models.Course;
import com.app.course.models.StudentWillLearn;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService service;

    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllCourse() {
        return service.getAllCourse();
    }

    @GetMapping("/review")
    public ResponseEntity<RepositoryObject> getAllReview() {
        return service.getAllReviewCourse();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCourseByUserId(@PathVariable long userId) {
        return service.findCourseByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getCourseById(@PathVariable long id) {
        return service.getCourseById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteCourseById(@PathVariable long id) {
        return service.deleteCourseById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateCourse(@RequestBody Course course, @PathVariable long id) {
        return service.updateCourseById(course, id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertCourse(@RequestBody Course course) {
        return service.insertCourse(course);
    }

    @PostMapping("/insert/user/{userId}")
    public ResponseEntity<?> insertCourse(@PathVariable long userId, @RequestBody Course course) {
        return service.insertCourse(userId, course);
    }

    @PostMapping("{courseId}/insert/clipDemo")
    public ResponseEntity<?> insertClipDem(@PathVariable long courseId, @RequestParam("file") MultipartFile fileVideo) {
        return service.insertClipDemoCourse(courseId, fileVideo);
    }

    @PostMapping("{courseId}/insert/img")
    public ResponseEntity<?> insertImg(@PathVariable long courseId, @RequestParam("file") MultipartFile fileImg) {
        return service.insertImgCourse(courseId, fileImg);
    }

    @PostMapping("{courseId}/insert/studyWillLearn")
    public ResponseEntity<?> insertWillStudyLearn(@RequestBody ArrayList<StudentWillLearn> studentWillLearns, @PathVariable long courseId) {
        return service.insertStudyWillLearn(courseId, studentWillLearns);
    }
}
