package com.app.course.controllers;

import com.app.course.models.SubRole;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.SubRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subrole")
public class SubRoleController {
    @Autowired
    SubRoleService service;

    @GetMapping()
    public ResponseEntity<RepositoryObject> getAllSubRole() {
        return service.getAllSubRole();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getSubRoleById(@PathVariable int id) {
        return service.getSubRoleById(id);
    }

    @PostMapping()
    public ResponseEntity<RepositoryObject> insertRole(@RequestBody SubRole subRole) {
        return service.insertSubRole(subRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateRole(@RequestBody SubRole subRole, @PathVariable int id) {
        return service.updateSubRole(subRole, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteSubRoleById(@PathVariable int id) {
        return service.deleteSubRoleById(id);
    }
}
