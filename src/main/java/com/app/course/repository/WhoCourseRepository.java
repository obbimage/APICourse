package com.app.course.repository;

import com.app.course.models.WhoCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  WhoCourseRepository extends JpaRepository<WhoCourse,Integer> {
    List<WhoCourse> findByCourseId(long id);
}
