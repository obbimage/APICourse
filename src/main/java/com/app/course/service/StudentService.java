package com.app.course.service;

import com.app.course.models.Student;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<RepositoryObject>  getALLStudent();
    ResponseEntity<RepositoryObject>  getStudentById(long id);
    ResponseEntity<RepositoryObject>  insertStudent(Student student);
    ResponseEntity<RepositoryObject> deleteStudentById(long id);
    ResponseEntity<RepositoryObject> UpdateStudentById(Student student, long id);



}
