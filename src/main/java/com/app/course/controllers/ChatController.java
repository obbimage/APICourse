package com.app.course.controllers;

import com.app.course.DTO.User.UserUpdateRequest;
import com.app.course.models.Chat;
import com.app.course.models.Unit;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService service;

    @GetMapping()
    public ResponseEntity<RepositoryObject> getAllChat() {
        return service.getAllChat();
    }

    // trả về danh sách rate theo courseId
    @GetMapping("/course/{courseId}")
    public ResponseEntity<RepositoryObject> getChatByCourseId(@PathVariable long courseId){
        return service.getAllChatByCourseId(courseId);
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<RepositoryObject> insertChat(@PathVariable long courseId,@RequestBody Chat chat) {
        return service.insertChat(chat, courseId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateChat(@RequestBody Chat chat, @PathVariable int id){
        return service.updateChat(chat,id);
    }
}
