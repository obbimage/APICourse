package com.app.course.service;

import com.app.course.models.Course;
import com.app.course.models.StudentWillLearn;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    ResponseEntity<RepositoryObject> getAllCourse();
    ResponseEntity<RepositoryObject> getAllReviewCourse(); // lây tất cả khóa học cần xét duyệt
    ResponseEntity<RepositoryObject> getCourseById(long id);
    ResponseEntity<RepositoryObject> insertCourse(Course course);
    ResponseEntity<RepositoryObject> insertCourse(long userId,Course course);
    ResponseEntity<RepositoryObject> insertImgCourse(long courseId, MultipartFile imgFile);
    ResponseEntity<RepositoryObject> insertClipDemoCourse(long courseId, MultipartFile imgFile);
    ResponseEntity<RepositoryObject> insertStudyWillLearn(long courseId,List<StudentWillLearn> studentWillLearns );

    ResponseEntity<RepositoryObject> findCourseByUserId(long id);
    ResponseEntity<RepositoryObject> deleteCourseById(long id);
    ResponseEntity<RepositoryObject> updateCourseById(Course course,long id);

}
