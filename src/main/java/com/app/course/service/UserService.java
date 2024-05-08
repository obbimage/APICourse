package com.app.course.service;

import com.app.course.models.User;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
    ResponseEntity<RepositoryObject> getAllUser();
    ResponseEntity<RepositoryObject> getUserById(long id);
    ResponseEntity<RepositoryObject> getUserByName(String userName);
    ResponseEntity<RepositoryObject> deleteUserById(long id);
    ResponseEntity<RepositoryObject> insertUser(User user);

    ResponseEntity<RepositoryObject> updateIfoUser(User user, long id);
    ResponseEntity<RepositoryObject> updatePassUser(User user, long id);
    ResponseEntity<RepositoryObject> updateAvatarUser(long id,MultipartFile file);
    ResponseEntity<RepositoryObject> changePassword(long idUser,String oldPassword,String newPassword);
//    ResponseEntity<RepositoryObject> updateImg(long id, MultipartFile file);
}
