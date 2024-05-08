package com.app.course.controllers;


import com.app.course.models.StudentWillLearn;
import com.app.course.service.StudentWillLearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/studentWillLearn")
public class StudentWillLearnController {
    @Autowired
    StudentWillLearnService studentWillLearnService;

    // them mot mang
    @PostMapping("/course/{courseId}")
    public ResponseEntity<?> insert(@PathVariable long courseId,@RequestBody ArrayList<StudentWillLearn> studentWillLearns) {
        return studentWillLearnService.insertStudentWillLearns(courseId, studentWillLearns);
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getByCourseId(@PathVariable int courseId){
        return studentWillLearnService.getStudentWillLearnsByCourseId(courseId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        return studentWillLearnService.deleteById(id);
    }


}
