package com.app.course.repository;

import com.app.course.models.Student;
import com.app.course.models.StudentWillLearn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
