package com.app.course.repository;

import com.app.course.models.LevelRequire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRequireRepository extends JpaRepository<LevelRequire, Integer> {
    List<LevelRequire> findByCourseId(long CourseId);
}
