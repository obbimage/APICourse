package com.app.course.service.section;

import com.app.course.models.Section;
import com.app.course.repository.RepositoryObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SectionService {
    ResponseEntity<RepositoryObject> getAllSection();
    ResponseEntity<RepositoryObject> getSectionById(int id);
    ResponseEntity<RepositoryObject> getSectionByUnitId(int id);
    ResponseEntity<RepositoryObject> insertSection(int unitId, List<Section> sections);

    ResponseEntity<RepositoryObject> insertSection(int unitId, Section section);
    ResponseEntity<RepositoryObject> insertSectionVideo(int sectionId, MultipartFile videoFile);
    ResponseEntity<RepositoryObject> updateSectionById(Section section,int id);
    ResponseEntity<RepositoryObject> deleteSectionById(int id);
}
