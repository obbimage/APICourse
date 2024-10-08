package com.app.course.service.unit;

import com.app.course.models.Unit;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UnitService {
    ResponseEntity<RepositoryObject> getAllUnit();
    ResponseEntity<RepositoryObject> getUnitById(int id);
    ResponseEntity<RepositoryObject> getByCourseId(long courseId);
    ResponseEntity<RepositoryObject> insertUnit(long courseId, List<Unit> unitList);
    ResponseEntity<RepositoryObject> insertUnit(long courseId,Unit unit);
    ResponseEntity<RepositoryObject> updateUnit(Unit unit, int id);
    ResponseEntity<RepositoryObject> deleteUnitById(int id);
}
