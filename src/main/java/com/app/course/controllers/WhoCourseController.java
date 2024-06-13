package com.app.course.controllers;

import com.app.course.models.WhoCourse;
import com.app.course.service.whoseCourse.WhoCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/whoCourse")
public class WhoCourseController {

    @Autowired
    WhoCourseService whoCourseService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourseId(@PathVariable long courseId) {
        return whoCourseService.getByCourseId(courseId);
    }

    @PostMapping("/insert/course/{courseId}")
    public ResponseEntity<?> insertWhoCourses(@PathVariable long courseId, @RequestBody ArrayList<WhoCourse> whoCourses) {
            return whoCourseService.insertWhoCourses(courseId, whoCourses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWhoCourseById(@PathVariable int whoCourseId) {
        return whoCourseService.deleteById(whoCourseId);
    }
}
