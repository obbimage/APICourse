package com.app.course.service;

import com.app.course.models.Role;
import com.app.course.models.SubRole;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface SubRoleService {
    ResponseEntity<RepositoryObject> getAllSubRole();
    ResponseEntity<RepositoryObject> getSubRoleById(int id);
    ResponseEntity<RepositoryObject> getSubRoleByRoleId(int id);
    ResponseEntity<RepositoryObject> insertSubRole(SubRole subRole);
    ResponseEntity<RepositoryObject> updateSubRole(SubRole newSubRole, int id);
    ResponseEntity<RepositoryObject> deleteSubRoleById(int id);
}
