package com.app.course.controllers;

import com.app.course.models.LevelRequire;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.LevelRequireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/levelRequire")
public class LevelRequireController {
    @Autowired
    LevelRequireService service;
    @GetMapping()
    public ResponseEntity<RepositoryObject> getAll(){
        return service.getAllLevelRequire();
    }
    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getById(@PathVariable int id){
        return service.getLevelRequireById(id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insert(@RequestBody LevelRequire levelRequire){
        return service.insertLevelRequire(levelRequire);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> update(@RequestBody LevelRequire levelRequire, @PathVariable int id){
        return service.updateLevelRequire(levelRequire,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteById(@PathVariable int id){
        return service.deleteLevelRequireById(id);
    }
}
