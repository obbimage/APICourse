package com.app.course.controllers;

import com.app.course.models.Unit;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    UnitService service;

    @GetMapping()
    public ResponseEntity<RepositoryObject> getAllUnit(){
        return service.getAllUnit();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getUnitById(@PathVariable int id){
        return service.getUnitById(id);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getUnitByCourseId(@PathVariable long courseId){
        return service.getByCourseId(courseId);
    }
    @PostMapping("/inserts/course/{courseId}")
    public ResponseEntity<RepositoryObject> insertUnit(@PathVariable long courseId, @RequestBody ArrayList<Unit> units){
        return service.insertUnit(courseId,units);
    }
    @PostMapping("/insert/course/{courseId}")
    public ResponseEntity<RepositoryObject> insertUnit(@PathVariable long courseId,@RequestBody Unit unit){
        return service.insertUnit(courseId, unit);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateUnit(@RequestBody Unit unit, @PathVariable int id){
        return service.updateUnit(unit,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteUnitById(@PathVariable int id){
        return service.deleteUnitById(id);
    }
}
