package com.app.course.repository;

import com.app.course.models.Rate;
import com.app.course.models.keys.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Integer> {
    boolean existsByCourse_idAndUser_id(long courseId, long userId);
    List<Rate> findByCourseId(long courseId);
}
