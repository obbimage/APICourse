package com.app.course.service.buy;

import com.app.course.models.Buy;
import com.app.course.models.Course;
import com.app.course.models.User;
import com.app.course.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class BuyServiceIpm implements BuyService {
    private final String QUERY_SUCCESS = "QUERY BUY SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CANT NOT FOUND WITH ID: ";
    @Autowired
    BuyRepository repository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<RepositoryObject> getAllBuy() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

//    @Override
//    public ResponseEntity<RepositoryObject> getBuyById(long studentId,long courseId) {
//        StudentCourseKey id = new  StudentCourseKey(studentId,courseId);
//        Optional<Buy> buy = repository.findById(id);
//        return buy.isPresent() ?
//                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll()) :
//                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND + id);
//    }

    @Override
    public ResponseEntity<RepositoryObject> insertBuy(long studentId, long courseId) {
        try {
            boolean exists = repository.existsByCourse_IdAndUser_Id(studentId,courseId);
            // nếu tồn tại thì dừng
            if(exists){
                return Response.resultFail();
            }

                Optional<User> userOptional = userRepository.findById(studentId);
                Optional<Course> courseOptional = courseRepository.findById(courseId);

                User user = userOptional.get();
                Course course = courseOptional.get();

                Buy buy = new Buy();

                buy.setUser(user);
                buy.setCourse(course);
                // get date now
                Date dateNow = new Date();
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
                String strDate = simpleDateFormat.format(dateNow);

                buy.setDateBuy(strDate);
                buy.setStudentComplete(false);
                return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(buy));
            // value complete default is false
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }

//    @Override
//    public ResponseEntity<RepositoryObject> updateBuy(Buy buy, long studentId, long courseId) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<RepositoryObject> deleteBuyById(long studentId, long courseId) {
//        StudentCourseKey id = new  StudentCourseKey(studentId,courseId);
//        boolean exist = repository.existsById(id);
//        if (exist) {
//            repository.deleteById(id);
//            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS);
//        } else {
//            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND + id);
//        }
//    }
}
