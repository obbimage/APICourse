package com.app.course.service;

import com.app.course.models.Unit;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import com.app.course.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UnitServiceIpm implements UnitService {
    private final String QUERY_SUCCESS = "QUERY UNIT SUCCESSFULLY";
    private final String CANT_NOT_FOUND = "CAN NOT FOUND WITH ID=";
    @Autowired
    UnitRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getAllUnit() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getUnitById(int id) {
        Optional<Unit> unit = repository.findById(id);

        return unit.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, unit) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND, "");

    }

    @Override
    public ResponseEntity<RepositoryObject> insertUnit(Unit unit) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(unit));
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage(), "");
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateUnit(Unit newUnit, int id) {
        Unit updateUnit = repository.findById(id).map(item -> {
            item.setTitle(newUnit.getTitle());
            return item;
        }).orElse(null);
        return updateUnit != null ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateUnit)) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND, "");

    }

    @Override
    public ResponseEntity<RepositoryObject> deleteUnitById(int id) {
        boolean exist = repository.existsById(id);
        if(exist){
            repository.deleteById(id);
           return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, "");
        }
        return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND, "");
    }
}
