package com.app.course.service;

import com.app.course.models.Test;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import com.app.course.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestServiceIpm implements TestService {

    private final String QUERY_SUCCESS = "QUERY TEST SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CAN NOT FOUND TEST WITH ID: ";
    @Autowired
    TestRepository repository;


    @Override
    public ResponseEntity<RepositoryObject> getAllTest() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getTestById(int id) {
        Optional<Test> test = repository.findById(id);
        return test.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, test) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
    }

    @Override
    public ResponseEntity<RepositoryObject> insertTest(Test test) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(test));
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.FAILED, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateTest(Test newTest, int id) {
        try {
            Test updatTest = repository.findById(id).map(item -> {
                item.setComplete(newTest.isComplete());
                return item;
            }).orElse(null);
            return updatTest != null ?
                    Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updatTest)) :
                    Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.FAILED, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteById(int id) {
        try {
            boolean exist = repository.existsById(id);
            if (exist) {
                repository.deleteById(id);
                return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS);
            } else {
                return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.FAILED, e.getMessage());
        }
    }
}
