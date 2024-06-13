package com.app.course.controllers;

import com.app.course.models.SubRole;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.subRole.SubRoleService;
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
    @GetMapping("/role/{roleId}")
    public ResponseEntity<RepositoryObject> getSubRoleByRoleId(@PathVariable int roleId){
        return service.getSubRoleByRoleId(roleId);
    }
    @PostMapping("/insert/role/{roleId}")
    public ResponseEntity<RepositoryObject> insertRole(@PathVariable int roleId,@RequestBody SubRole subRole) {
        return service.insertSubRole(roleId,subRole);
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
