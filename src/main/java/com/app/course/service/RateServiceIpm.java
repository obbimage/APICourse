package com.app.course.service;

import com.app.course.models.Rate;
import com.app.course.models.keys.StudentCourseKey;
import com.app.course.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RateServiceIpm implements RateService {
    private final String QUERY_SUCCESS = "QUERY RATE SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CAN NOT FOUND RATE ";
    @Autowired
    RateRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getAllRate() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getRateById(long studentId, long courseId) {
        StudentCourseKey id = new StudentCourseKey(studentId, courseId);
        Optional<Rate> rate = repository.findById(id);
        return rate.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll()) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
    }

    @Override
    public ResponseEntity<RepositoryObject> insertRate(Rate rate, long studentId, long courseId) {
        try {
            StudentCourseKey id = new StudentCourseKey(studentId, courseId);
            boolean exist = repository.existsById(id);
            if (exist) {
                return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.FAILED, "take early");
            } else {
                // set id for rate
                rate.setId(id);
                return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(rate));
            }
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED,Status.ERR,e.getMessage());
        }

    }

    @Override
    public ResponseEntity<RepositoryObject> updateRate(Rate newRate, long studentId, long courseId) {
        StudentCourseKey id = new StudentCourseKey(studentId, courseId);
        Rate updateRate = repository.findById(id).map(item -> {
            item.setComment(newRate.getComment());
            return item;
        }).orElse(null);
        return updateRate != null ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateRate)) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteRateById(long studentId, long courseId) {
        StudentCourseKey id = new StudentCourseKey(studentId, courseId);
        //find Rate by id
        boolean exist = repository.existsById(id);
        // rm if exist
        if (exist) {
            repository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS);
        } else {
            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
        }
    }

}
