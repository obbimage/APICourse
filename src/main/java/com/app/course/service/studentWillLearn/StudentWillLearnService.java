package com.app.course.service.studentWillLearn;

import com.app.course.models.StudentWillLearn;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface  StudentWillLearnService {

    ResponseEntity<RepositoryObject> insertStudentWillLearns (long courseId,List<StudentWillLearn> studentWillLearns);
    ResponseEntity<RepositoryObject> insertStudentWillLearn(long courseId,StudentWillLearn studentWillLearn);
    ResponseEntity<RepositoryObject> getStudentWillLearnsByCourseId(int courseId);
    ResponseEntity<RepositoryObject> deleteById(int studyWillLean);
}
