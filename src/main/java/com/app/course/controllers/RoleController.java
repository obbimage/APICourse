package com.app.course.controllers;

import com.app.course.models.Role;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService service;

    @GetMapping("")
    public ResponseEntity<RepositoryObject> getRoleAll(){
        return service.getAllRole();
    }

//    @GetMapping("/course/{courseId}")
//    public ResponseEntity<RepositoryObject> getRoleByCourseId(@PathVariable long courseId){
//        return service.getRoleByCourseId(courseId);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getRoleById(@PathVariable int id){
        return service.getRoleById(id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertRole(@RequestBody Role role){
        return  service.insertRole(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateRole(@RequestBody Role role, @PathVariable int id){
        return service.updateRoleById(role,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteRoleById(@PathVariable int id){
        return service.deleteRoleById(id);
    }
}
