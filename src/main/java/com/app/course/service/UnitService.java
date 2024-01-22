package com.app.course.service;

import com.app.course.models.Unit;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface UnitService {
    ResponseEntity<RepositoryObject> getAllUnit();
    ResponseEntity<RepositoryObject> getUnitById(int id);
    ResponseEntity<RepositoryObject> insertUnit(Unit unit);
    ResponseEntity<RepositoryObject> updateUnit(Unit unit, int id);
    ResponseEntity<RepositoryObject> deleteUnitById(int id);
}
