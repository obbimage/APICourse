package com.app.course.controllers;

import com.app.course.models.Question;
import com.app.course.repository.RepositoryObject;
import com.app.course.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService service;

    @GetMapping("")
    public ResponseEntity<RepositoryObject> getAllQuestion(){
        return service.getAllQuestion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepositoryObject> getQuestionById(@PathVariable int id){
        return service.getQuestionById(id);
    }
    @PostMapping("")
    public ResponseEntity<RepositoryObject> insertQuestion(@RequestBody Question question){
        return service.insertQuestion(question);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepositoryObject> updateQuestion(@RequestBody Question question, @PathVariable int id){
        return service.updateQuestion(question,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepositoryObject> deleteQuestionById(@PathVariable int id){
        return service.deleteQuestionById(id);
    }
}
