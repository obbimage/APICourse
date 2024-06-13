package com.app.course.service.test;

import com.app.course.models.Test;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface TestService {

    ResponseEntity<RepositoryObject> getAllTest();
    ResponseEntity<RepositoryObject> getTestById(int id);
    ResponseEntity<RepositoryObject> insertTest(Test test);
    ResponseEntity<RepositoryObject> updateTest(Test newTest,int id);
    ResponseEntity<RepositoryObject> deleteById(int id);

}
