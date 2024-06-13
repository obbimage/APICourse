package com.app.course.controllers;

import com.app.course.models.Educator;
import com.app.course.repository.RepositoryObject;
import com.app.course.security.user.UserSecurityService;
import com.app.course.service.educator.EducatorService;
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

    @PutMapping("/update/{educatorId}")
    public ResponseEntity<RepositoryObject> updateEducator(@PathVariable long educatorId, @RequestBody Educator educator){
        return educatorService.updateEducator(educator,educatorId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteEducatorById(@PathVariable long id){
        return  educatorService.deleteEducatorById(id);
    }
}
