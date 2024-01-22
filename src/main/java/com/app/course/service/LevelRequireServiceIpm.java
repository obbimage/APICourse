package com.app.course.service;

import com.app.course.models.LevelRequire;
import com.app.course.repository.LevelRequireRepository;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LevelRequireServiceIpm implements LevelRequireService {
    private final String QUERY_SUCCESS = "QUERY LEVEL REQUIRE SUCCESSFULLY";
    private final String CANT_NOT_FOUND = "CANT NOT FOUND WITH ID: ";
    @Autowired
    LevelRequireRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getAllLevelRequire() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getLevelRequireById(int id) {
        Optional<LevelRequire> levelRequire = repository.findById(id);
        return levelRequire.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findById(id)) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND + id, "");
    }

    @Override
    public ResponseEntity<RepositoryObject> insertLevelRequire(LevelRequire levelRequire) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(levelRequire));
        } catch (DataAccessException e) {

            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateLevelRequire(LevelRequire newLevelRequire, int id) {
        try {
            LevelRequire updateLeveRequire = repository.findById(id).map(item -> {
                item.setLevel(newLevelRequire.getLevel());
                return item;
            }).orElse(null);
            return updateLeveRequire != null ?
                    Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateLeveRequire)) :
                    Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND);
        } catch (Exception e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteLevelRequireById(int id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS);
        } else {
            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND);
        }
    }
}
