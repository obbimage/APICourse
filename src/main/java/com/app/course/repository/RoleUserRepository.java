package com.app.course.repository;

import com.app.course.models.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository extends JpaRepository<RoleUser,Integer> {
}
