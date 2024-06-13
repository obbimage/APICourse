package com.app.course.service.course;

import com.app.course.models.Course;
import com.app.course.models.StudentWillLearn;
import com.app.course.repository.RepositoryObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    ResponseEntity<RepositoryObject> getAllCourse();
    // lấy danh sách người dùng từ pageNumber với số lượng pageSize;
    ResponseEntity<RepositoryObject> getToPage(int pageNumber,int pageSize);
    // lay course sort theo ngay create
    ResponseEntity<RepositoryObject> getAllOrderByDateUpload(int numberPage, int pageSize);
    // tìm kiếm tất cả khóa học có chứa ký tự
    ResponseEntity<RepositoryObject> getAllContainName(String courseName,int pageNumber,int pageSize );
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

    // trả về tất cả các khóa học mà user đã đăng ký
    ResponseEntity<RepositoryObject> findAllCourseBuy(HttpServletRequest httpServletRequest);

    ResponseEntity<RepositoryObject>  isBuyCourse(long courseId, long userId);
    ResponseEntity<RepositoryObject> getCourseFromBuy(long courseId);

    ResponseEntity<RepositoryObject> setCompleteCourse(long courseId,boolean complete);
    ResponseEntity<RepositoryObject> getAllCourseByComplete(boolean complete);
}
