package com.app.course.service.language;

import com.app.course.models.Language;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface LanguageService {
    ResponseEntity<RepositoryObject> getAllLanguage();
    ResponseEntity<RepositoryObject> getLanguageById(int id);
    ResponseEntity<RepositoryObject> deleteLanguageById(int id);
    ResponseEntity<RepositoryObject> insertLanguage( Language language);
    ResponseEntity<RepositoryObject> updateLanguage(Language newLanguage, int id);
}
