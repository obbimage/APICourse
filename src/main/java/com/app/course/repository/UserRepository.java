package com.app.course.repository;

import com.app.course.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    Optional<User> findByEmail(String mail);

    boolean existsByUsername(String userName);
//    User findById(long id);
    List<User> findByRole(String userRole);

    Page<User> findByRole(String userRole, Pageable pageable);
    Page<User> findById(long id, Pageable pageable);
    @Query(value = "SELECT u FROM User u WHERE u.firstName LIKE %:keyword% OR u.phone LIKE %:keyword%")
    Page<User> searchUserByIdOrNameOrPhone(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT u FROM User u WHERE u.id = :id OR u.firstName LIKE %:keyword% OR u.phone LIKE %:keyword%")
    Page<User> searchUserByIdOrNameOrPhone(@Param("id") Long id, @Param("keyword") String keyword, Pageable pageable);

}
