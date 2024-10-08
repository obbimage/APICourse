package com.app.course.service.rate;

import com.app.course.models.Rate;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface RateService {
    ResponseEntity<RepositoryObject> getAllRate();
    ResponseEntity<RepositoryObject> getAllRateByCourseId(long userId) ;
    ResponseEntity<RepositoryObject> insertRate(Rate rate,long courseId);
//    ResponseEntity<RepositoryObject> updateRate(Rate newRate, long studentId,long courseId);
//    ResponseEntity<RepositoryObject> deleteRateById(long studentId,long courseId);

}
