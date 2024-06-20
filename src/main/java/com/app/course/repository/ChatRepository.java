package com.app.course.repository;

import com.app.course.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    boolean existsByCourse_idAndUser_id(long courseId, long userId);
    List<Chat> findByCourseId(long courseId);
}
