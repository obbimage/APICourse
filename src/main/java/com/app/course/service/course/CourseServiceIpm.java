package com.app.course.service.course;

import com.app.course.config.AlertQuery;
import com.app.course.models.Course;
import com.app.course.models.FileUploadResponse;
import com.app.course.models.StudentWillLearn;
import com.app.course.models.User;
import com.app.course.repository.*;
import com.app.course.security.jwt.JwtTokenProvider;
import com.app.course.security.user.UserSecurity;
import com.app.course.security.user.UserSecurityService;
import com.app.course.service.email.EmailService;
import com.app.course.service.file.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceIpm implements CourseService {
    private final String QUERY_SUCCESS = "QUERY COURSE SUCCESSFULLY";
    private final String CANT_NOT_FOUND = "CAN NOT FOUND COURSE WIH ID= ";
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FileService fileService;
    @Autowired
    BuyRepository buyRepository;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private EmailService emailService;
    public CourseServiceIpm(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<RepositoryObject> getAllCourse() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, courseRepository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getToPage(int pageNumber, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Course> response = courseRepository.findAll(pageable);
            return Response.resultOk(response);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> getAllOrderByDateUpload(int numberPage, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(numberPage, pageSize);
            Page<Course> response = courseRepository.findAllOrderDateUpload(pageable);
            return Response.resultOk(response);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> getAllContainName(String courseName, int numberPage, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(numberPage, pageSize);
            Page<Course> response = courseRepository.findByNameContaining(courseName, pageable);
            return Response.resultOk(response);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> getAllReviewCourse() {
        var response = courseRepository.findAll();
        return Response.resultOk(response);
    }

    @Override
    public ResponseEntity<RepositoryObject> getCourseById(long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, course) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND, "");

    }

    @Override
    public ResponseEntity<RepositoryObject> insertCourse(Course course) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, courseRepository.save(course));

        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage(), "");
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertCourse(long userId, Course course) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                // set user cho course
                User user = userOptional.get();
                course.setUser(user);
                // cap nhat date cho lan dau tao khoa hoc
                long courseId = course.getId();
                // chưa có trong csdl
                if (courseId == 0) {
                    // get date now
                    Date dateNow = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = simpleDateFormat.format(dateNow);

                    course.setDateUpload(strDate);
                }
                var response = courseRepository.save(course);
                return Response.resultOk(response);
            } else {
                return Response.resultFail();
            }
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertImgCourse(long courseId, MultipartFile imgFile) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                // xoa file truoc khi them
                String img = course.getImg();
                if (img != null) {
                    String fileName = fileService.getNameFileFromDownLoadUrl(img);
                    fileService.deleteFileImg(fileName);
                }
                FileUploadResponse fileUploadResponse = fileService.uploadImg(imgFile);
                String urlDownLoadFile = fileUploadResponse.getDownloadUrl();
                if (urlDownLoadFile != null) {
                    course.setImg(urlDownLoadFile);
                    var response = courseRepository.save(course);
                    return Response.resultOk(response);
                } else {
                    return Response.resultFail();
                }
            } else {
                return Response.resultFail();
            }
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertClipDemoCourse(long courseId, MultipartFile videoFile) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                // xoa file truoc khi them
                String clipDem = course.getClipDemo();
                if (clipDem != null) {
                    String fileName = fileService.getNameFileFromDownLoadUrl(clipDem);
                    fileService.deleteFileVideo(fileName);
                }
                FileUploadResponse fileUploadResponse = fileService.uploadVideo(videoFile);
                String downLoadUrl = fileUploadResponse.getDownloadUrl();
                if (downLoadUrl != null) {
                    course.setClipDemo(downLoadUrl);
                    var response = courseRepository.save(course);
                    return Response.resultOk(response);
                } else {
                    return Response.resultFail();
                }
            } else {
                return Response.resultFail();
            }
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> findCourseByUserId(long id) {
        try {
            boolean userExit = userRepository.existsById(id);
            if (!userExit) {
                return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
            }
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, courseRepository.findByUserId(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteCourseById(long id) {
        boolean exist = courseRepository.existsById(id);
        if (exist) {
            courseRepository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, "");
        } else {
            return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND, "");
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateCourseById(Course newCourse, long id) {
        Course updateCourse = courseRepository.findById(id).map(item -> {
            item.setClipDemo(newCourse.getClipDemo());
            item.setName(newCourse.getName());
            item.setTitle(newCourse.getTitle());
            item.setDescription(newCourse.getDescription());
            item.setPrice(newCourse.getPrice());
            item.setRole(newCourse.getRole());
            item.setSubRole(newCourse.getSubRole());
            return item;
        }).orElse(null);
        return updateCourse != null ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, courseRepository.save(updateCourse)) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND + id, "");
    }

    @Override
    public ResponseEntity<RepositoryObject> findAllCourseBuy(HttpServletRequest httpServletRequest) {
        try {
            var userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userSecurity.getUser();
            long userId = user.getId();
            var response = buyRepository.getAllCourseFromBuy(userId);
            return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, response);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> isBuyCourse(long courseId, long userId) {
        boolean courseExists = buyRepository.existsByCourse_IdAndUser_Id(courseId, userId);
        if (courseExists)
            return Response.resultOk(true);
        return Response.resultOk(false);
    }

    @Override
    public ResponseEntity<RepositoryObject> getCourseFromBuy(long courseId) {
        try {
            var userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userSecurity.getUser();
            long userId = user.getId();
            Course course = buyRepository.getCourseFromBuy(userId, courseId);
            if (course == null)
                return Response.resultFail();
            return Response.resultOk(course);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> setCompleteCourse(long courseId, boolean complete) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isEmpty())
                return Response.resultFail();

            Course course = courseOptional.get();
            course.setComplete(complete);
            var response = courseRepository.save(course);
            return Response.resultOk(response);
        } catch (Exception e) {
           return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> setConfirmCourse(long courseId, boolean confirm) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isEmpty())
            return Response.resultFail();
        Course course = courseOptional.get();
        course.setConfirm(confirm);

        Optional<User> userOptional = userRepository.findByCourseId(courseId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            emailService.sendEmail(user.getEmail(),"Duyệt khóa học", "Khóa học " + course.getName() +" đã được duyệt");
        }

        return Response.resultOk(courseRepository.save(course));
    }

    @Override
    public ResponseEntity<RepositoryObject> getAllCourseByComplete(boolean complete) {
        var response = courseRepository.findByCompleteAndConfirm(complete,false);
        return Response.resultOk(response);
    }


    // bi loi khong su dung
    @Override
    public ResponseEntity<RepositoryObject> insertStudyWillLearn(long courseId, List<StudentWillLearn> studentWillLearns) {
        try {
            Optional<Course> courseOptional = courseRepository.findById(courseId);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                course.setStudentWillLearns(studentWillLearns);
                courseRepository.save(course);
                return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, course);
            } else {
                return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CANT_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, AlertQuery.ERR);
        }
    }


}
