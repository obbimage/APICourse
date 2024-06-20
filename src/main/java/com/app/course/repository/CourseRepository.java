package com.app.course.repository;

import com.app.course.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUserId(long id);
    List<Course> findByComplete(boolean complete);
    List<Course> findByCompleteAndConfirm(boolean complete, boolean allow);
    List<Course> findByConfirm(boolean confirm);
    @Query("SELECT c FROM Course c ORDER BY STR_TO_DATE(c.dateUpload,'%d%m%y') ")
    Page<Course> findAllOrderDateUpload(Pageable pageable);

    Page<Course> findByNameLike(String pattern,Pageable pageable);

    Page<Course> findByNameContaining(String nameCourse, Pageable pageable);

}
