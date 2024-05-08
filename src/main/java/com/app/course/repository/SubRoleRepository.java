package com.app.course.repository;

import com.app.course.models.SubRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubRoleRepository extends JpaRepository<SubRole,Integer> {
    List<SubRole> findByRoleId(int id);
}
