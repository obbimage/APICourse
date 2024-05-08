package com.app.course.service;

import com.app.course.config.AlertQuery;
import com.app.course.models.Course;
import com.app.course.models.Unit;
import com.app.course.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceIpm implements UnitService {
    private final String QUERY_SUCCESS = "QUERY UNIT SUCCESSFULLY";
    private final String CANT_NOT_FOUND = "CAN NOT FOUND WITH ID=";
    @Autowired
    UnitRepository repository;
    @Autowired
    CourseRepository courseRepository;

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
    public ResponseEntity<RepositoryObject> getByCourseId(long courseId) {
        try {
            var response = repository.findByCourseId(courseId);
            return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, response);
        } catch (Exception e) {

            return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertUnit(long courseId, List<Unit> units) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                List<Unit> unitResponse = new ArrayList<>();
                for (Unit unit : units) {
                    unit.setCourse(course);
                    var response = repository.save(unit);
                    unitResponse.add(response);
                }
                return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, unitResponse);
            } else {
                return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
            }
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.BAD_REQUEST, Status.ERR, e.getMessage(), "");
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertUnit(long courseId, Unit unit) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                unit.setCourse(course);
                var response = repository.save(unit);
                return Response.resultOk(response);
            } else {
                return Response.resultFail();
            }
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
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
        if (exist) {
            repository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, "");
        }
        return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND, "");
    }
}
