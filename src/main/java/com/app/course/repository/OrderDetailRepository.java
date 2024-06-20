package com.app.course.repository;

import com.app.course.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Query(value = "SELECT od FROM OrderDetail od JOIN od.course c JOIN c.user u WHERE c.user.id = :userId ")
    List<OrderDetail> findByUserIdForCourse(long userId);
}
