package com.app.course.repository;

import com.app.course.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
