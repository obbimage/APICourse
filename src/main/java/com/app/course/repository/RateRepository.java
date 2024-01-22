package com.app.course.repository;

import com.app.course.models.Rate;
import com.app.course.models.keys.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, StudentCourseKey> {
}
