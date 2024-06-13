package com.app.course.service.roleUser;

import com.app.course.config.AlertQuery;
import com.app.course.models.RoleUser;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.RoleUserRepository;
import com.app.course.repository.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceIpm implements  RoleUserService{
    @Autowired
    private RoleUserRepository repository;
    @Override
    public ResponseEntity<RepositoryObject> getAllRoleUser() {
        return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS,repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> insertRoleUser(RoleUser roleUser) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS,repository.save(roleUser));
        }catch (DataAccessException e){
            return Response.result(HttpStatus.OK, Status.OK, e.getMessage());
        }

    }
}
