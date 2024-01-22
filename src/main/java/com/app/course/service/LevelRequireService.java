package com.app.course.service;

import com.app.course.models.LevelRequire;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface LevelRequireService {
    ResponseEntity<RepositoryObject> getAllLevelRequire();
    ResponseEntity<RepositoryObject> getLevelRequireById(int id);
    ResponseEntity<RepositoryObject> insertLevelRequire(LevelRequire levelRequire);
    ResponseEntity<RepositoryObject> updateLevelRequire(LevelRequire levelRequire, int id);
    ResponseEntity<RepositoryObject> deleteLevelRequireById(int id);
}
