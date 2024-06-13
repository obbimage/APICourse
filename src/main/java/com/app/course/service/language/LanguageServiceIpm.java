package com.app.course.service.language;

import com.app.course.models.Language;
import com.app.course.repository.LanguageRepository;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceIpm implements LanguageService {
    @Autowired
    LanguageRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getAllLanguage() {
        var response = repository.findAll();
        return Response.resultOk(response);
    }

    /*
     * @AUTHOR: SINH TIEN
     * @SINCE: 8/26/2023 10:25 PM
     * @DESCRIPTION:  get Language, if not exit throw err
     * @UPDATE:
     *
     * */
    @Override
    public ResponseEntity<RepositoryObject> getLanguageById(int id) {
        Optional<Language> language = repository.findById(id);
        return language.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RepositoryObject("ok", "Query language successfully", language)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RepositoryObject("failed", "can not find language with " + id, "")
                );
    }

    /*
    * @AUTHOR: SINH TIEN
    * @SINCE: 8/27/2023 12:07 PM
    * @DESCRIPTION:  delete by id
    * @UPDATE:
    *
    * */
    @Override
    public ResponseEntity<RepositoryObject> deleteLanguageById(int id) {
        boolean exist = repository.existsById(id);
        if(exist){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RepositoryObject("ok", "delete language successfully","")
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RepositoryObject("failed","not found language","")
        );
    }

    /*
     * @AUTHOR: SINH TIEN
     * @SINCE: 8/27/2023 12:26 AM
     * @DESCRIPTION:  if language not exit, insert at db
     * @UPDATE:
     *
     * */
    @Override
    public ResponseEntity<RepositoryObject> insertLanguage(Language language) {
        List<Language> foundLanguage = repository.findByName(language.getName().trim());
        return !foundLanguage.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new RepositoryObject("failed", "language already taken", "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new RepositoryObject("ok", "insert language successfully", repository.save(language))
                );
    }

    /*
     * @AUTHOR: SINH TIEN
     * @SINCE: 8/27/2023 12:44 AM
     * @DESCRIPTION:  update if exit language, insert if not exit
     * @UPDATE:
     *
     * */
    @Override
    public ResponseEntity<RepositoryObject> updateLanguage(Language newLanguage, int id) {
        try {
            Language updateLanguage = repository.findById(id).map(
                    language -> {
                        language.setName(newLanguage.getName());
                        return language;
                    }).orElseGet(() -> {
                newLanguage.setId(id);
                return newLanguage;
            });
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RepositoryObject("ok", "update language successfully", repository.save(updateLanguage))
            );
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RepositoryObject("failed", e.getMessage(), "")
            );
        }
    }


}
