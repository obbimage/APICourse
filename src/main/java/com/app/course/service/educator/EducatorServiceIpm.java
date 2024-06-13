package com.app.course.service.educator;

import com.app.course.models.Educator;
import com.app.course.repository.EducatorRepository;
import com.app.course.repository.RepositoryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EducatorServiceIpm implements EducatorService {
    @Autowired
    EducatorRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getAllLEducator() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RepositoryObject("ok", "query educator successfully", repository.findAll())
        );
    }

    @Override
    public ResponseEntity<RepositoryObject> getEducatorById(long id) {
        Optional<Educator> educator = repository.findById(id);
        return educator.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RepositoryObject("ok", "query educator successfully", educator)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RepositoryObject("failed", "can not found educator with id= " + id, "")
                );
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteEducatorById(long id) {
        boolean educator = repository.existsById(id);
        if (educator) {
            repository.deleteById(id);
            // alert for client
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RepositoryObject("failed", "query educator successfully", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RepositoryObject("failed", "can not found educator with id= " + id, "")
            );
        }

    }

//    @Override
//    public ResponseEntity<RepositoryObject> insertEducator(Educator educator) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new RepositoryObject("ok", "query educator successfully", repository.save(educator))
//            );
//        } catch (DataAccessException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                    new RepositoryObject("err", e.getMessage(), "")
//            );
//        }
//    }

    @Override
    public ResponseEntity<RepositoryObject> updateEducator(Educator newEducator, long id) {
        try {
            Educator educator = repository.findById(id).map(item -> {
                item.setBiography(newEducator.getBiography());
                item.setDescription(newEducator.getDescription());
                return item;
            }).orElse(null);

            return educator == null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RepositoryObject("ok", "query educator successfully", repository.save(educator))
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RepositoryObject("err", "can not found educator with id= " + id, repository.save(educator))
                    );
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RepositoryObject("err", e.getMessage(), "")
            );
        }

    }
}
