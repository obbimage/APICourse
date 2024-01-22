package com.app.course.service;

import com.app.course.models.Role;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.RoleRepository;
import com.app.course.repository.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceIpm implements RoleService {
    private final String QUERY_SUCCESS = "QUERY ROLE SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CAN NOT FOUND ROLE WITH ID: ";
    @Autowired
    RoleRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getAllRole() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getRoleById(int id) {
        Optional<Role> role = repository.findById(id);
        return role.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, role) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND + id);
    }

    @Override
    public ResponseEntity<RepositoryObject> insertRole(Role role) {
        try {
           return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(role));
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateRoleById(Role newRole, int id) {
        try {
            Role updateRole = repository.findById(id).map(item -> {
                item.setName(newRole.getName());
                return item;
            }).orElse(null);
            return updateRole != null ?
                    Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateRole)) :
                    Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND + id);
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteRoleById(int id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS);
        } else {
            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND + id);
        }
    }
}
