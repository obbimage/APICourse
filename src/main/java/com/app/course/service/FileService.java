package com.app.course.service;

import com.app.course.models.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileUploadResponse uploadFile(MultipartFile file);
    byte[] getFile(String fileName);

    boolean deleteFile(String fileName);



}
