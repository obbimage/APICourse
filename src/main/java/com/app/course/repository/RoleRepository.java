package com.app.course.repository;

import com.app.course.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    //    Optional<Role> findByCourseId(long courseId);
    Optional<Role> findByName(String name);
    boolean existsByName(String name);
    @Query("SELECT r.name FROM Role r")
    List<String> findAllRoleNames();
}
