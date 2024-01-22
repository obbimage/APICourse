package com.app.course.controllers;

import com.app.course.models.Section;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/section")
public class SectionController {
    @Autowired
    SectionService service;
    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllSection(){
        return service.getAllSection();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getSectionById(@PathVariable int id){
        return service.getSectionById(id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertSection(@RequestBody Section section){
        return service.insertSection(section);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateSection(@RequestBody Section section ,@PathVariable int id){
        return service.updateSectionById(section,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteSectionById(@PathVariable int id){
        return service.deleteSectionById(id);
    }
}
