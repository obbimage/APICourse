package com.app.course.service;

import com.app.course.DTO.User.UserDTO;
import com.app.course.DTO.User.UserUpdateRequest;
import com.app.course.config.AlertQuery;
import com.app.course.config.Constants;
import com.app.course.mapper.user.UserMapper;
import com.app.course.mapper.user.UserUpdateRequestMapper;
import com.app.course.models.FileUploadResponse;
import com.app.course.models.User;
import com.app.course.repository.*;
import com.app.course.security.SecurityConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceIpm implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EducatorRepository educatorRepository;

    @Autowired
    FileService fileService;

    /*
     * @AUTHOR: SINH TIEN
     * @SINCE: 8/27/2023 1:50 PM
     * @DESCRIPTION:  get all user
     * @UPDATE:
     *
     * */
    @Override
    public ResponseEntity<RepositoryObject> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(new RepositoryObject("ok", "query user successfully ", userRepository.findAll()));
    }

    /*
     * @AUTHOR: SINH TIEN
     * @SINCE: 8/27/2023 1:50 PM
     * @DESCRIPTION:  get user by id
     * @UPDATE:
     *
     * */
    @Override
    public ResponseEntity<RepositoryObject> getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(new RepositoryObject("ok", "query user successfully", user)) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RepositoryObject("failed", "cant not found user with id=" + id, ""));
    }

    @Override
    public ResponseEntity<RepositoryObject> getUserByName(String userName) {
        User user = userRepository.findByUsername(userName);
        return user != null ? ResponseEntity.status(HttpStatus.OK).body(new RepositoryObject("ok", "query user successfully", user)) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RepositoryObject("failed", "cant not found with user=" + userName, ""));
    }

    /*
     * @AUTHOR: SINH TIEN
     * @SINCE: 8/27/2023 1:51 PM
     * @DESCRIPTION:  delete user by id
     * @UPDATE:
     *
     * */
    @Override
    public ResponseEntity<RepositoryObject> deleteUserById(long id) {
        boolean exist = userRepository.existsById(id);
        if (exist) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RepositoryObject("ok", "query user successfully", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RepositoryObject("failed", "cant not found user with id=" + id, ""));
        }

    }

    /*
     * @AUTHOR: SINH TIEN
     * @SINCE: 8/27/2023 1:51 PM
     * @DESCRIPTION:  insert user
     * @UPDATE:
     *
     * */
    @Override
    public ResponseEntity<RepositoryObject> insertUser(User user) {
        User users = userRepository.findByUsername(user.getUsername().trim());
        // if user not exist then insert
        return users == null ? ResponseEntity.status(HttpStatus.OK).body(new RepositoryObject("ok", "query user successfully", userRepository.save(user))) :
                // status 409 -> xảy ra sung đột
                ResponseEntity.status(HttpStatus.CONFLICT).body(new RepositoryObject("failed", "use already taken ", ""));

    }



    @Override
    public ResponseEntity<RepositoryObject> updateInfoUser(UserUpdateRequest newInfoRequest, long userId){
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                UserUpdateRequestMapper.INSTANCE.updateUser(user,newInfoRequest);
                var response = userRepository.save(user);
                return Response.resultOk(response);
            }
            return Response.resultFail();
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }
    @Override
    public ResponseEntity<RepositoryObject> updatePassUser(User newUser, long id) {
        try {
            // update from new user to old user, return if user cant not found
            User updateUser = userRepository.findById(id).map(user -> {
                user.setPassword(newUser.getPassword());
                return user;
            }).orElse(null);

            // save user at db if found user with param id
            return updateUser != null ? ResponseEntity.status(HttpStatus.OK).body(new RepositoryObject("ok", "query user successfully", userRepository.save(updateUser))) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RepositoryObject("failed", "cant not user with id= " + id, ""));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RepositoryObject("err", e.getMessage(), ""));
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateAvatarUser(long id, MultipartFile file) {
        try {
//       tim user
            Optional<User> userOptional = userRepository.findById(id);
            // neu co thi tien hanh update avartar
            if (userOptional.isPresent()) {
                // lay user tu optional
                User user = userOptional.get();
                // nếu đã có avatar trước đó thì xóa ảnh cux trước khi thêm vào
                String downLoadUrlAvatar = user.getAvatar();
                if(downLoadUrlAvatar != null){
                    String fileName = fileService.getNameFileFromDownLoadUrl(downLoadUrlAvatar);
                    boolean isDelete = fileService.deleteFile(fileName);
                }
                // up file vao server
                FileUploadResponse fileUploadResponse = fileService.uploadFile(file);

                user.setAvatar(fileUploadResponse.getDownloadUrl());
                userRepository.save(user);
                return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, user);
            }
            // khong tim thay
            else {
                return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, AlertQuery.ERR);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, AlertQuery.ERR);
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> changePassword(long idUser, String oldPassword, String newPassword, HttpServletRequest httpServletRequest) {
        Optional<User> userOptional = userRepository.findById(idUser);
        try {
            // nếu id hợp lệ và đúng password thì cho phép đổi mật khẩu
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                // nếu mật khẩu đúng thì cho phép đổi mật khẩu
                if (SecurityConfig.passwordEncoder().matches(oldPassword, user.getPassword())) {
                    // bam mật khẩu trước khi lưu
                    String encodePassword = SecurityConfig.passwordEncoder().encode(newPassword);
                    user.setPassword(encodePassword);
                    return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, userRepository.save(user));
                }
                // Nếu mật khẩu không đúng
                else {
                    log.warn("changePassword: password incorrect");
                    return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
                }
            }
            // không tìm thấy user từ id
            else {
                log.warn("changePassword: id not exits");
                return Response.result(HttpStatus.OK, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("changePassword:" + e.getMessage());
            return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, AlertQuery.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> getAllEducatorToUser() {
        var educators = userRepository.findByRole(Constants.ROLE_EDUCATOR);
        return Response.resultOk(educators);
    }
}
