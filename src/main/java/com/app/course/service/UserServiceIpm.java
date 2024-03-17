package com.app.course.service;

import com.app.course.models.User;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIpm implements UserService {
    @Autowired
    UserRepository repository;

    /*
     * @AUTHOR: SINH TIEN
     * @SINCE: 8/27/2023 1:50 PM
     * @DESCRIPTION:  get all user
     * @UPDATE:
     *
     * */
    @Override
    public ResponseEntity<RepositoryObject> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RepositoryObject("ok", "query user successfully ", repository.findAll())
        );
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
        Optional<User> user = repository.findById(id);
        return user.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RepositoryObject("ok", "query user successfully", user)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RepositoryObject("failed", "cant not found user with id=" + id, "")
                );
    }

    @Override
    public ResponseEntity<RepositoryObject> getUserByName(String userName) {
        User user = repository.findByUsername(userName);
        return user != null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RepositoryObject("ok", "query user successfully", user)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RepositoryObject("failed", "cant not found with user=" + userName , "")
                );
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
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RepositoryObject("ok", "query user successfully", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RepositoryObject("failed", "cant not found user with id=" + id, "")
            );
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
        User users = repository.findByUsername(user.getUsername().trim());
        // if user not exist then insert
        return users == null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RepositoryObject("ok", "query user successfully", repository.save(user))
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RepositoryObject("failed", "use already taken ", "")
                );

    }

    @Override
    public ResponseEntity<RepositoryObject> updateIfoUser(User newUser, long id) {
        try {
            // update from new user to old user, return if user cant not found
            User updateUser = repository.findById(id).map(user -> {
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setEmail(newUser.getEmail());
                user.setPhone(newUser.getPhone());
                user.setAddress(newUser.getAddress());
                return user;
            }).orElse(null);

            // save user at db if found user with param id
            return updateUser != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RepositoryObject("ok", "query user successfully", repository.save(updateUser))
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RepositoryObject("failed", "cant not user with id= " + id, "")
                    );
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RepositoryObject("err", e.getMessage(), "")
            );
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updatePassUser(User newUser, long id) {
        try {
            // update from new user to old user, return if user cant not found
            User updateUser = repository.findById(id).map(user -> {
                user.setPassword(newUser.getPassword());
                return user;
            }).orElse(null);

            // save user at db if found user with param id
            return updateUser != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RepositoryObject("ok", "query user successfully", repository.save(updateUser))
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RepositoryObject("failed", "cant not user with id= " + id, "")
                    );
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RepositoryObject("err", e.getMessage(), "")
            );
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateAvatarUser(User newUser, long id) {
        try {
            // update from new user to old user, return if user cant not found
            User updateUser = repository.findById(id).map(user -> {
                user.setAvatar(newUser.getAvatar());
                return user;
            }).orElse(null);

            // save user at db if found user with param id
            return updateUser != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RepositoryObject("ok", "query user successfully", repository.save(updateUser))
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RepositoryObject("failed", "cant not user with id= " + id, "")
                    );
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RepositoryObject("err", e.getMessage(), "")
            );
        }
    }

}
