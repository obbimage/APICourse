package com.app.course.service;

import com.app.course.config.AlertQuery;
import com.app.course.models.FileUploadResponse;
import com.app.course.models.Section;
import com.app.course.models.Unit;
import com.app.course.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceIpm implements SectionService {
    private final String QUERY_SUCCESS = "QUERY SECTION SUCCESSFULLY";
    private final String CAN_NOT_FOUND = "CANT NOT FOUND WITH ID: ";
    @Autowired
    SectionRepository repository;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    FileService fileService;

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
    public ResponseEntity<RepositoryObject> getSectionByUnitId(int id) {
        try {
            var response = repository.findByUnitIdOrderByNumberSection(id);
            return Response.resultOk(response);
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertSection(int unitId, List<Section> sections) {
        try {
            Optional<Unit> unitOptional = unitRepository.findById(unitId);
            if (unitOptional.isPresent()) {
                Unit unit = unitOptional.get();
                List<Section> sectionResponse = new ArrayList<>();
                for (Section section : sections) {
                    section.setUnit(unit);
                    var response = repository.save(section);
                    sectionResponse.add(response);
                }
                return Response.result(HttpStatus.OK, Status.OK, AlertQuery.QUERY_SUCCESS, sectionResponse);
            } else {
                return Response.result(HttpStatus.BAD_REQUEST, Status.FAILED, AlertQuery.CANT_NOT_FOUND);
            }
        } catch (Exception e) {
            return Response.result(HttpStatus.BAD_REQUEST, Status.ERR, e.getMessage());

        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertSection(int unitId, Section section) {
        try {
            Optional<Unit> unitOptional = unitRepository.findById(unitId);
            if (unitOptional.isPresent()) {
                Unit unit = unitOptional.get();
                section.setUnit(unit);
                var response = repository.save(section);
                return Response.resultOk(response);
            } else {
                return Response.resultFail();
            }
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RepositoryObject> insertSectionVideo(int sectionId, MultipartFile videoFile) {
        try {
            Optional<Section> sectionOptional = repository.findById(sectionId);
            if (sectionOptional.isPresent()) {
                Section section = sectionOptional.get();
                String urlVideo = section.getUrlVideo();
                // xóa file cũ nếu có tồn tại
                if (urlVideo != null && !urlVideo.isEmpty()) {
                    // lay file name tu down load url
                    String fileNameVideo = fileService.getNameFileFromDownLoadUrl(urlVideo);
                    fileService.deleteFileVideo(fileNameVideo);
                }
                FileUploadResponse fileUploadResponse = fileService.uploadVideo(videoFile);
                String downLoadFileUrl = fileUploadResponse.getDownloadUrl();
                // quăng ra lỗi nếu thêm file thất bại
                if (downLoadFileUrl == null) {
                    throw new NullPointerException("insert file failed");
                }
                section.setUrlVideo(downLoadFileUrl);
                var response = repository.save(section);
                return Response.resultOk(response);
            } else {
                return Response.resultFail();
            }
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
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
        try {
            boolean exist = repository.existsById(id);
            if (exist) {
                repository.deleteById(id);
                return Response.result(HttpStatus.OK, Status.OK, QUERY_SUCCESS, "");
            } else {
                return Response.result(HttpStatus.NOT_IMPLEMENTED, Status.FAILED, CAN_NOT_FOUND, "");
            }
        } catch (Exception e) {
            return Response.resultError(e.getMessage());
        }
    }
}
