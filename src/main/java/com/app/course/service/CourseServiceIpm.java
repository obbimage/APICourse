package com.app.course.service;

import com.app.course.models.Course;
import com.app.course.repository.CourseRepository;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceIpm implements CourseService {
    private final String QUERY_SUCCESS = "QUERY COURSE SUCCESSFULLY";
    private final String CANT_NOT_FOUND = "CAN NOT FOUND COURSE WIH ID= ";
    @Autowired
    CourseRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getCourseAll() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getCourseById(long id) {
        Optional<Course> course = repository.findById(id);
        return course.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, course) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND, "");

    }

    @Override
    public ResponseEntity<RepositoryObject> insertCourse(Course course) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(course));

        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage(), "");
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> findCourseByUserId(long id) {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findByUserId(id));
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteCourseById(long id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, "");
        } else {
            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND, "");
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateCourseById(Course newCourse, long id) {
        Course updateCourse = repository.findById(id).map(item -> {
            item.setClipDemo(newCourse.getClipDemo());
            item.setLevelRequire(newCourse.getLevelRequire());
            item.setName(newCourse.getName());
            item.setTitle(newCourse.getTitle());
            item.setDescription(newCourse.getDescription());
            item.setPrice(newCourse.getPrice());
            item.setRole(newCourse.getRole());
            item.setSubRole(newCourse.getSubRole());
            return item;
        }).orElse(null);
        return updateCourse != null ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateCourse)) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND + id, "");
    }
}
