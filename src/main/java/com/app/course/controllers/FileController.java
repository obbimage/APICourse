package com.app.course.controllers;

import com.app.course.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService service;

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        var fileBytes = service.getFile(fileName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileBytes);
    }

    @PostMapping("/")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) {
        try {
            var fileUploadResponse = service.uploadFile(file);
            return ResponseEntity.ok(fileUploadResponse);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("File upload unsuccessful!");
        }
    }

    @DeleteMapping("/{file}")
    public ResponseEntity<?> deleteFile(@PathVariable String file){
        if(service.deleteFile(file)){
            return ResponseEntity
                    .ok()
                    .body("file delete successful");
        }else{
            return ResponseEntity
                    .internalServerError()
                    .body("File upload unsuccessful!");
        }
    }

}
