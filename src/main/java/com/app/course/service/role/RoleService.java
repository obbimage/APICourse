package com.app.course.service.role;


import com.app.course.models.Role;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<RepositoryObject> getAllRole();
    ResponseEntity<RepositoryObject> getRoleById(int id);
//    ResponseEntity<RepositoryObject> getRoleByCourseId(long courseId);
    ResponseEntity<RepositoryObject> insertRole(Role role);
    ResponseEntity<RepositoryObject> updateRoleById(Role newRole, int id);
    ResponseEntity<RepositoryObject> deleteRoleById(int id);
    ResponseEntity<RepositoryObject> toggleRole(int roleId);
}
