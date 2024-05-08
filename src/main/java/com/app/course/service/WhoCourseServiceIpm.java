package com.app.course.service;

import com.app.course.config.AlertQuery;
import com.app.course.models.Course;
import com.app.course.models.WhoCourse;
import com.app.course.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WhoCourseServiceIpm implements WhoCourseService {
    @Autowired
    WhoCourseRepository whoCourseRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public ResponseEntity<RepositoryObject> getByCourseId(long courseId) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isPresent()) {
                var response = whoCourseRepository.findByCourseId(courseId);
                return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, response);
            } else {
                return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
            }
        } catch (Exception e) {
            return Response.result(HttpStatus.BAD_REQUEST, Status.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertWhoCourses(long courseId, List<WhoCourse> whoCourses) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            // neu course id co ton tai
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                // Lửu response để trả về cho client
                List<WhoCourse> whoCourseResponse = new ArrayList<>();
                for (WhoCourse whoCourse : whoCourses) {
                    whoCourse.setCourse(course);
                    var response = whoCourseRepository.save(whoCourse);
                    whoCourseResponse.add(response);
                }
                return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, whoCourseResponse);
            } else {
                return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
            }
        } catch (Exception e) {
            return Response.result(HttpStatus.BAD_REQUEST, Status.ERR, AlertQuery.ERR);
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteById(int id) {
        try {
            boolean exist = whoCourseRepository.existsById(id);
            if(exist){
                whoCourseRepository.deleteById(id);
                return Response.result(HttpStatus.OK,Status.OK,AlertQuery.QUERY_SUCCESS);
            }else{
                return Response.result(HttpStatus.NOT_FOUND,Status.FAILED,AlertQuery.CANT_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.result(HttpStatus.BAD_REQUEST,Status.ERR,e.getMessage());
        }
    }
}
