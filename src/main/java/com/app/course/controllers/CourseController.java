package com.app.course.controllers;

import com.app.course.models.Course;
import com.app.course.models.StudentWillLearn;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.course.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService service;

    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllCourse() {
        return service.getAllCourse();
    }
    @GetMapping("asc/dateUpload/page/{pageNumber}/{pageSize}")
    public ResponseEntity<?> getAllCourseAscDateUpload(@PathVariable int pageNumber, @PathVariable int pageSize){
        return service.getAllOrderByDateUpload(pageNumber,pageSize);
    }
    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity<?> getAllCourse(@PathVariable int pageNumber, @PathVariable int pageSize){
        return service.getToPage(pageNumber,pageSize);
    }
    @GetMapping("/search/name/{courseName}/page/{pageNumber}/{pageSize}")
    public ResponseEntity<?> findByName(@PathVariable String courseName,@PathVariable int pageNumber, @PathVariable int pageSize){
        return service.getAllContainName(courseName,pageNumber,pageSize);
    }
    @GetMapping("/review")
    public ResponseEntity<RepositoryObject> getAllReview() {
        return service.getAllReviewCourse();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCourseByUserId(@PathVariable long userId) {
        return service.findCourseByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getCourseById(@PathVariable long id) {
        return service.getCourseById(id);
    }

    // trả về tất cả khóa học mà user đã mua
    @GetMapping("/buy")
    public ResponseEntity<RepositoryObject> getAllCourseBuy(HttpServletRequest httpServletRequest){
        return  service.findAllCourseBuy(httpServletRequest);
    }
    // cho biet khoa hoc da duoc dang ky hay chua
    @GetMapping("buying/course/{courseId}/user/{userId}")
    public ResponseEntity<?> isBuyCourse(@PathVariable long courseId, @PathVariable long userId){
        return service.isBuyCourse(courseId,userId);
    }
    @GetMapping("/{courseId}/buy")
    public ResponseEntity<?> getCourseBuying(@PathVariable long courseId){
        return service.getCourseFromBuy(courseId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteCourseById(@PathVariable long id) {
        return service.deleteCourseById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateCourse(@RequestBody Course course, @PathVariable long id) {
        return service.updateCourseById(course, id);
    }

    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertCourse(@RequestBody Course course) {
        return service.insertCourse(course);
    }

    @PostMapping("/insert/user/{userId}")
    public ResponseEntity<?> insertCourse(@PathVariable long userId, @RequestBody Course course) {
        return service.insertCourse(userId, course);
    }
    // set complete
    @PostMapping("/{courseId}/{complete}")
    public ResponseEntity<?> setComplete(@PathVariable int courseId,@PathVariable boolean complete){
        return service.setCompleteCourse(courseId, complete);
    }
    @PostMapping("/complete/{complete}")
    public ResponseEntity<?> getComplete(@PathVariable boolean complete){
        return service.getAllCourseByComplete(complete);
    }
    @PostMapping("{courseId}/insert/clipDemo")
    public ResponseEntity<?> insertClipDem(@PathVariable long courseId, @RequestParam("file") MultipartFile fileVideo) {
        return service.insertClipDemoCourse(courseId, fileVideo);
    }

    @PostMapping("{courseId}/insert/img")
    public ResponseEntity<?> insertImg(@PathVariable long courseId, @RequestParam("file") MultipartFile fileImg) {
        return service.insertImgCourse(courseId, fileImg);
    }

    @PostMapping("{courseId}/insert/studyWillLearn")
    public ResponseEntity<?> insertWillStudyLearn(@RequestBody ArrayList<StudentWillLearn> studentWillLearns, @PathVariable long courseId) {
        return service.insertStudyWillLearn(courseId, studentWillLearns);
    }

}
