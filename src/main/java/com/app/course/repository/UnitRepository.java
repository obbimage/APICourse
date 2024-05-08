package com.app.course.repository;

import com.app.course.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Unit> findByCourseId(long courseId);
}
