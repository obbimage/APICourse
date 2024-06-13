package com.app.course.service.question;

import com.app.course.models.Question;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface QuestionService {
    ResponseEntity<RepositoryObject> getAllQuestion();
    ResponseEntity<RepositoryObject> getQuestionById(int id);
    ResponseEntity<RepositoryObject> insertQuestion(Question question);
    ResponseEntity<RepositoryObject> updateQuestion(Question question, int id);
    ResponseEntity<RepositoryObject> deleteQuestionById(int id);
}
