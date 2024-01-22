package com.app.course.service;

import com.app.course.models.Question;
import com.app.course.repository.QuestionRepository;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceIpm implements QuestionService {
    private final String QUERY_SUCCESS = "QUERY QUESTION SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CAN NOT FOUND QUESTION";
    @Autowired
    QuestionRepository repository;


    @Override
    public ResponseEntity<RepositoryObject> getAllQuestion() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getQuestionById(int id) {
        Optional<Question> question = repository.findById(id);
        return question.isPresent() ?
                Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, question) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
    }

    @Override
    public ResponseEntity<RepositoryObject> insertQuestion(Question question) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS,repository.save(question));

        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateQuestion(Question question, int id) {
        try {
            Question updateQuestion = repository.findById(id).map(item -> {
                item.setQuestion(question.getQuestion());
                item.setAnswer(question.getAnswer());
                item.setChoose1(question.getChoose1());
                item.setChoose2(question.getChoose2());
                item.setChoose3(question.getChoose3());
                return item;
            }).orElse(null);
            return updateQuestion != null ?
                    Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateQuestion)) :
                    Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);

        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteQuestionById(int id) {
        try {
            boolean exist = repository.existsById(id);
            if (exist) {
                repository.deleteById(id);
                return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS);
            } else {
                return Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND);
            }
        } catch (DataAccessException e) {

            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage());
        }
    }
}
