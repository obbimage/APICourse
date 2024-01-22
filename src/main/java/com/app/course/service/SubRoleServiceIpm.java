package com.app.course.service;

import com.app.course.models.SubRole;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import com.app.course.repository.SubRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubRoleServiceIpm implements SubRoleService {
    private final String QUERY_SUCCESS = "QUERY SUB ROLE SUCCESSFULLY ";
    private final String CAN_NOT_FOUND = "CAN NOT FOUND SUB ROLE WITH ID: ";
    @Autowired
    SubRoleRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getAllSubRole() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getSubRoleById(int id) {
        Optional<SubRole> subRole = repository.findById(id);
        return subRole.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, subRole) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
    }

    @Override
    public ResponseEntity<RepositoryObject> insertSubRole(SubRole subRole) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(subRole));
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateSubRole(SubRole newSubRole, int id) {
        try {
            // find role with id, return null if id not found
            SubRole updateSubRole = repository.findById(id).map(item -> {
                item.setName(newSubRole.getName());
                return item;
            }).orElse(null);

            // update if found with id
            // if not exist alert cant not found
            return updateSubRole != null ?
                    Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateSubRole)) :
                    Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND + id);
        } catch (DataAccessException e) {
            // alert err
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }

    }

    /*
    * @AUTHOR: SINH TIEN
    * @SINCE: 9/1/2023 2:57 PM
    * @DESCRIPTION:  find with id, if exist than delete sub role, if not found than alert not found with id
    * @UPDATE:
    *
    * */
    @Override
    public ResponseEntity<RepositoryObject> deleteSubRoleById(int id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS);
        }else {
            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND + id);
        }
    }
}
