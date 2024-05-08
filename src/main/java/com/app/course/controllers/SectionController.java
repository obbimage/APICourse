package com.app.course.controllers;

import com.app.course.models.Section;
import com.app.course.models.Unit;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

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

    @GetMapping("/unit/{unitId}")
    public ResponseEntity<?> getSectionByUnitId(@PathVariable int unitId){
        return  service.getSectionByUnitId(unitId);
    }
    @PostMapping("/inserts/unit/{unitId}")
    public ResponseEntity<?> insertsWithCourseId(@PathVariable int unitId, @RequestBody ArrayList<Section> sections){
        return  service.insertSection(unitId,sections);
    }
    @PostMapping("{sectionId}/insert/video")
    public ResponseEntity<?> insertVideo(@PathVariable int sectionId, @RequestParam("file") MultipartFile videoFile){
        return service.insertSectionVideo(sectionId,videoFile);
    }
    @PostMapping("/insert/unit/{unitId}")
    public ResponseEntity<?> insetWithCourseId(@PathVariable int unitId, @RequestBody Section section){
        return service.insertSection(unitId,section);
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
