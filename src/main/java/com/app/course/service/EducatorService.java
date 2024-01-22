package com.app.course.service;

import com.app.course.models.Educator;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EducatorService {
    ResponseEntity<RepositoryObject> getAllLEducator();
    ResponseEntity<RepositoryObject> getEducatorById(long id);
    ResponseEntity<RepositoryObject> deleteEducatorById(long id);
    ResponseEntity<RepositoryObject> updateEducator(Educator newEducator, long id);
}
