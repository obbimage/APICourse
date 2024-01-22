package com.app.course.service;

import com.app.course.models.Section;
import com.app.course.repository.RepositoryObject;
import com.app.course.repository.Response;
import com.app.course.repository.SectionRepository;
import com.app.course.repository.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionServiceIpm implements SectionService {
    private final String QUERY_SUCCESS = "QUERY SECTION SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CANT NOT FOUND WITH ID: ";
    @Autowired
    SectionRepository repository;

    @Override
    public ResponseEntity<RepositoryObject> getAllSection() {
        return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.findAll());
    }

    @Override
    public ResponseEntity<RepositoryObject> getSectionById(int id) {
        Optional<Section> section = repository.findById(id);
        return section.isPresent() ? Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, section) : Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND + id, "");
    }

    @Override
    public ResponseEntity<RepositoryObject> insertSection(Section section) {
        try {
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(section));
        } catch (DataAccessException e) {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.ERR, e.getMessage(), "");
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> updateSectionById(Section newSection, int id) {
        Section updateSection = repository.findById(id).map(item -> {
            item.setContent(newSection.getContent());
            item.setTitle(newSection.getTitle());
            return item;
        }).orElse(null);
        return updateSection != null ? Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, repository.save(updateSection)) :
                Response.result(HttpStatus.NOT_FOUND, Status.FAILED, CAN_NOT_FOUND, "");
    }

    @Override
    public ResponseEntity<RepositoryObject> deleteSectionById(int id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, "");
        } else {
            return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.FAILED, CAN_NOT_FOUND, "");
        }
    }
}
