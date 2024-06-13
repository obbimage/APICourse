package com.app.course.service.levelRquire;

import com.app.course.models.LevelRequire;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LevelRequireService {
    ResponseEntity<RepositoryObject> getAllLevelRequire();

    ResponseEntity<RepositoryObject> getLevelRequireById(int id);

    ResponseEntity<RepositoryObject> getLevelRequireByCourseId(long courseId);

    ResponseEntity<RepositoryObject> insertLevelRequire(LevelRequire levelRequire);

    ResponseEntity<RepositoryObject> insertLevelRequires(long courseId, List<LevelRequire> levelRequires);

    ResponseEntity<RepositoryObject> updateLevelRequire(LevelRequire levelRequire, int id);

    ResponseEntity<RepositoryObject> deleteLevelRequireById(int id);
}
