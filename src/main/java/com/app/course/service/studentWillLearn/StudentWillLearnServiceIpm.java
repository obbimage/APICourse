package com.app.course.service.studentWillLearn;

import com.app.course.config.AlertQuery;
import com.app.course.models.Course;
import com.app.course.models.StudentWillLearn;
import com.app.course.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentWillLearnServiceIpm implements  StudentWillLearnService{
    @Autowired
    StudentWillLearnRepository repository;
    @Autowired
    CourseRepository courseRepository;
    @Override
    public ResponseEntity<RepositoryObject> insertStudentWillLearns(long courseId,List<StudentWillLearn> studentWillLearns) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Course course = courseOptional.get();
        for(StudentWillLearn studentWillLearn : studentWillLearns){
            studentWillLearn.setCourse(course);
            repository.save(studentWillLearn);
        }
        return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS);
    }

    @Override
    public ResponseEntity<RepositoryObject> insertStudentWillLearn(long courseId,StudentWillLearn studentWillLearn) {
        return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS,repository.save(studentWillLearn));
    }

    @Override
    public ResponseEntity<RepositoryObject> getStudentWillLearnsByCourseId(int courseId) {
        List<StudentWillLearn> studentWillLearns = repository.findByCourseId(courseId);
        return !studentWillLearns.isEmpty() ?
                Response.result(HttpStatus.OK,Status.OK ,AlertQuery.QUERY_SUCCESS,studentWillLearns ):
                Response.result(HttpStatus.NOT_FOUND,Status.FAILED,AlertQuery.CANT_NOT_FOUND);
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteById(int studyWillLeanId) {
        boolean exist = repository.existsById(studyWillLeanId);
        if (exist) {
            repository.deleteById(studyWillLeanId);
            return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, "");
        } else {
            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, AlertQuery.CANT_NOT_FOUND, "");
        }
    }
}
