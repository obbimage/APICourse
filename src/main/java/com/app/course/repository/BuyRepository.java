package com.app.course.repository;

import com.app.course.models.Buy;
import com.app.course.models.keys.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, StudentCourseKey> {
    List<Buy> findByStudentId(long id);
}
