package com.app.course.service.rate;

import com.app.course.models.Course;
import com.app.course.models.Rate;
import com.app.course.models.User;
import com.app.course.repository.*;
import com.app.course.security.user.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateServiceIpm implements RateService {
    private final String QUERY_SUCCESS = "QUERY RATE SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CAN NOT FOUND RATE ";
    @Autowired
    RateRepository rateRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<RepositoryObject> getAllRate() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, rateRepository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getAllRateByCourseId(long courseId) {
        List<Rate> rates = rateRepository.findByCourseId(courseId);
//        if (rates.isEmpty())
//            return Response.resultFail();

        return Response.resultOk(rates);
    }

    //    @Override
//    public ResponseEntity<RepositoryObject> getRateById(long studentId, long courseId) {
//        StudentCourseKey id = new StudentCourseKey(studentId, courseId);
//        Optional<Rate> rate = repository.findById(id);
//        return rate.isPresent() ?
//                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll()) :
//                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
//    }
//
    @Override
    public ResponseEntity<RepositoryObject> insertRate(Rate rate, long courseId) {
        try {

            UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long userId = userSecurity.getUser().getId();

            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Course> courseOptional = courseRepository.findById(courseId);

            User user = userOptional.get();
            Course course = courseOptional.get();


            boolean exists = rateRepository.existsByCourse_idAndUser_id(courseId, userId);
//            if(!exists)
//                return Response.resultFail();
            rate.setUser(user);
            rate.setCourse(course);

            var response = rateRepository.save(rate);
            return Response.resultOk(response);
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.BAD_REQUEST, Status.ERR, e.getMessage());
        }

    }
//
//    @Override
//    public ResponseEntity<RepositoryObject> updateRate(Rate newRate, long studentId, long courseId) {
//        StudentCourseKey id = new StudentCourseKey(studentId, courseId);
//        Rate updateRate = repository.findById(id).map(item -> {
//            item.setComment(newRate.getComment());
//            return item;
//        }).orElse(null);
//        return updateRate != null ?
//                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateRate)) :
//                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
//    }
//
//    @Override
//    public ResponseEntity<RepositoryObject> deleteRateById(long studentId, long courseId) {
//        StudentCourseKey id = new StudentCourseKey(studentId, courseId);
//        //find Rate by id
//        boolean exist = repository.existsById(id);
//        // rm if exist
//        if (exist) {
//            repository.deleteById(id);
//            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS);
//        } else {
//            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
//        }
//    }

}
