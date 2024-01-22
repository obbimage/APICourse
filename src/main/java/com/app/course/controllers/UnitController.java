package com.app.course.controllers;

import com.app.course.models.Unit;
import com.app.course.repository.EducatorRepository;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertUnit(@RequestBody Unit unit){
        return service.insertUnit(unit);
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
