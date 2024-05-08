package com.app.course.repository;

import com.app.course.models.StudentWillLearn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentWillLearnRepository  extends JpaRepository<StudentWillLearn,Integer> {
    List<StudentWillLearn> findByCourseId(int id);
}
