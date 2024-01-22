package com.app.course.service;

import com.app.course.models.Course;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface CourseService {
    ResponseEntity<RepositoryObject> getCourseAll();
    ResponseEntity<RepositoryObject> getCourseById(long id);
    ResponseEntity<RepositoryObject> insertCourse(Course course);

    ResponseEntity<RepositoryObject> findCourseByUserId(long id);
    ResponseEntity<RepositoryObject> deleteCourseById(long id);
    ResponseEntity<RepositoryObject> updateCourseById(Course course,long id);

}
