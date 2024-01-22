package com.app.course.controllers;

import com.app.course.models.Student;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
    @Autowired
    StudentService service;
    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllStudent(){
        return service.getALLStudent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getStudentById(@PathVariable long id){
        return service.getStudentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateStudentById(@RequestBody Student student,@PathVariable long id){
        return service.UpdateStudentById(student,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteStudentById(@PathVariable long id){
        return service.deleteStudentById(id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertStudent(@RequestBody Student student){
        return service.insertStudent(student);
    }


}
