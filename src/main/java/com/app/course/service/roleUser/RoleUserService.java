package com.app.course.service.roleUser;

import com.app.course.models.RoleUser;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface RoleUserService {

    ResponseEntity<RepositoryObject> getAllRoleUser();
    ResponseEntity<RepositoryObject> insertRoleUser(RoleUser roleUser);

}
