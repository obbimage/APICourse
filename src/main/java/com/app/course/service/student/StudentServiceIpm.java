package com.app.course.service.student;

import com.app.course.models.Student;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import com.app.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceIpm implements StudentService {
    String QUERY_SUCCESS = "query student successfully";
    String CANT_NOT_FOUND_WITH = "cant not found student with id: ";
    @Autowired
    StudentRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getALLStudent() {
        return Response.result(HttpStatus.OK, "ok", QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getStudentById(long id) {
        Optional<Student> student = repository.findById(id);
        return student.isPresent() ?
                Response.result(HttpStatus.OK, "ok", QUERY_SUCCESS, repository.findById(id)) :
                Response.result(HttpStatus.NOT_FOUND, "failed", CANT_NOT_FOUND_WITH + id, "");
    }

    @Override
    public ResponseEntity<RepositoryObject> insertStudent(Student student) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(student));
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage(), "");
        }
    }

    /*
    * @AUTHOR: SINH TIEN
    * @SINCE: 8/28/2023 6:37 PM
    * @DESCRIPTION:  not delete because one to one way
    * @UPDATE:
    *
    * */
    @Override
    public ResponseEntity<RepositoryObject> deleteStudentById(long id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, "");
        } else {

            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND_WITH + id, "");
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> UpdateStudentById(Student newStudent, long id) {
        Student updateStudent = repository.findById(id).map(item -> {
                    item.setLevel(newStudent.getLevel());
                    item.setExperience(newStudent.getExperience());
                    return item;
                }
        ).orElse(null);
        return updateStudent != null ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateStudent)) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND_WITH + id, "");
    }
}
