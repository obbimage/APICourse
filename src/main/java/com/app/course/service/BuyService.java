package com.app.course.service;

import com.app.course.models.Buy;
import com.app.course.models.keys.StudentCourseKey;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface BuyService {
    ResponseEntity<RepositoryObject> getAllBuy();
//    ResponseEntity<RepositoryObject> getBuyById(long studentId,long courseId);
    ResponseEntity<RepositoryObject> insertBuy(long studentId,long courseId);
//    ResponseEntity<RepositoryObject> updateBuy(Buy buy, long studentId,long courseId);
//    ResponseEntity<RepositoryObject> deleteBuyById(long studentId,long courseId);

}
