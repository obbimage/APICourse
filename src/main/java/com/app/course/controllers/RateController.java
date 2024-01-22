package com.app.course.controllers;

import com.app.course.models.Rate;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class RateController {

    @Autowired
    RateService service;

    @GetMapping()
  public   ResponseEntity<RepositoryObject> getAllRate(){
        return service.getAllRate();
    }
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<RepositoryObject> getRatById(@PathVariable long studentId,@PathVariable long courseId){
        return service.getRateById(studentId,courseId);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<RepositoryObject> insertRate(@RequestBody Rate rate,
                                                       @PathVariable long studentId,@PathVariable long courseId){
        return service.insertRate(rate,studentId,courseId);
    }

    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<RepositoryObject> updateRate(@RequestBody Rate rate,
                                                       @PathVariable long studentId, @PathVariable long courseId){
        return service.updateRate(rate,studentId,courseId);
    }
}
