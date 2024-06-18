package com.app.course.service.chat;

import com.app.course.models.Chat;
import com.app.course.models.Course;
import com.app.course.models.User;
import com.app.course.repository.*;
import com.app.course.security.user.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceIpm implements ChatService {
    private final String QUERY_SUCCESS = "QUERY RATE SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CAN NOT FOUND RATE ";
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<RepositoryObject> getAllChat() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, chatRepository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getAllChatByCourseId(long courseId) {
        List<Chat> chats = chatRepository.findByCourseId(courseId);
//        if (rates.isEmpty())
//            return Response.resultFail();

        return Response.resultOk(chats);
    }

    @Override
    public ResponseEntity<RepositoryObject> insertChat(Chat chat, long courseId) {
        try {

            UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long userId = userSecurity.getUser().getId();

            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Course> courseOptional = courseRepository.findById(courseId);

            User user = userOptional.get();
            Course course = courseOptional.get();


            boolean exists = chatRepository.existsByCourse_idAndUser_id(courseId, userId);
//            if(!exists)
//                return Response.resultFail();
            chat.setUser(user);
            chat.setCourse(course);

            var response = chatRepository.save(chat);
            return Response.resultOk(response);
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.BAD_REQUEST, Status.ERR, e.getMessage());
        }

    }


}
