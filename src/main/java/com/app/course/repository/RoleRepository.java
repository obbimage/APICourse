package com.app.course.repository;

import com.app.course.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Optional<Role> findByCourseId(long courseId);
}
