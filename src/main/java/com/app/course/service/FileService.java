package com.app.course.service;

import com.app.course.models.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileUploadResponse uploadFile(MultipartFile file);

    FileUploadResponse uploadFile(String path, MultipartFile file);

    FileUploadResponse uploadImg(MultipartFile imgFile);
    FileUploadResponse uploadVideo(MultipartFile file);

    byte[] getFile(String fileName);

    byte[] getFile(String path, String fileName);
    byte[] getFileImg(String fileName);
    byte[] getFileVideo(String fileName);

    boolean deleteFile(String fileName);
    boolean deleteFileVideo(String fileName);
    boolean deleteFileImg(String fileName);

    String getDownLoadUrl();

    String getNameFileFromDownLoadUrl(String downloadUrl);

}
