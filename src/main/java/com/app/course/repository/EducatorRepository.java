package com.app.course.repository;

import com.app.course.models.Educator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducatorRepository extends JpaRepository<Educator,Long> {
    List<Educator> findByUserId(long id);
}
