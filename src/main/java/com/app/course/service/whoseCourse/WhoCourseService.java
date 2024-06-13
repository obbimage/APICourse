package com.app.course.service.whoseCourse;

import com.app.course.models.WhoCourse;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WhoCourseService {
    ResponseEntity<RepositoryObject> getByCourseId(long courseId);
    ResponseEntity<RepositoryObject> insertWhoCourses(long courseId, List<WhoCourse> whoCourses);
    ResponseEntity<RepositoryObject> deleteById(int id);
}
