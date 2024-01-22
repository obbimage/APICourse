package com.app.course.repository;

import com.app.course.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LanguageRepository extends JpaRepository<Language,Integer> {
    List<Language> findByName(String name);
}
