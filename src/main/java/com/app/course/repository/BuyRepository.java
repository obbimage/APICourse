package com.app.course.repository;

import com.app.course.models.Buy;
import com.app.course.models.Course;
import com.app.course.models.keys.StudentCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, Integer> {
    boolean existsByCourse_IdAndUser_Id(long courseId, long userId);

    @Query("SELECT b.course FROM Buy b WHERE b.user.id = :userId")
    List<Course> getAllCourseFromBuy(long userId);

    @Query("SELECT b.course FROM Buy b WHERE b.user.id = :userId AND b.course.id = :courseId")
    Course getCourseFromBuy(@Param("userId") long userId, @Param("courseId") long courseId);
}
