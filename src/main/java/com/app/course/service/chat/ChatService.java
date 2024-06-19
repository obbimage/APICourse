package com.app.course.service.chat;

import com.app.course.models.Chat;
import com.app.course.models.Unit;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface ChatService {
    ResponseEntity<RepositoryObject> getAllChat();
    ResponseEntity<RepositoryObject> getAllChatByCourseId(long userId) ;
    ResponseEntity<RepositoryObject> insertChat(Chat chat,long courseId);
    ResponseEntity<RepositoryObject> updateChat(Chat chat, int id);
}
