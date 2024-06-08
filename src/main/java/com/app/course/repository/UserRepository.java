package com.app.course.repository;

import com.app.course.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);

    boolean existsByUsername(String userName);
//    User findById(long id);

}
