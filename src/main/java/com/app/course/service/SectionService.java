package com.app.course.service;

import com.app.course.models.Section;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;

public interface SectionService {
    ResponseEntity<RepositoryObject> getAllSection();
    ResponseEntity<RepositoryObject> getSectionById(int id);
    ResponseEntity<RepositoryObject> insertSection(Section section);
    ResponseEntity<RepositoryObject> updateSectionById(Section section,int id);
    ResponseEntity<RepositoryObject> deleteSectionById(int id);
}
