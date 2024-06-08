package com.app.course.repository;

import com.app.course.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Integer> {
    List<Section> findByUnitId(int id);
    List<Section> findByUnitIdOrderByNumberSection(int unitId);
}
