package com.app.course.service;

import com.app.course.models.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileServiceIpm implements  FileService{
    @Value("${file.upload-path}")
    private String uploadPath;

    private String downloadUrl = "http://localhost:8080/files/";
    @Override
    public FileUploadResponse uploadFile(MultipartFile file) {
        // Get original filename
        var originalFileName = file.getOriginalFilename();

        // Extract file extension from original file name
        // Egg: image.jpg -> .jpg
        var fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // create random file name
        var randomFileName = UUID.randomUUID() + fileExtension;

        // create target path
        var targetPath = this.uploadPath +"/"+randomFileName;

        // save file
        // check if upload path directory exist or not
        try {
            if(!Files.exists(Path.of(uploadPath))) {
                Files.createDirectories(Path.of(uploadPath));
            }
            // save file
            Files.copy(file.getInputStream(),Path.of(targetPath));

            // return file upload response
            FileUploadResponse fileUploadResponse = new FileUploadResponse();
            fileUploadResponse.setFileName(randomFileName);
            downloadUrl += randomFileName;
            fileUploadResponse.setDownloadUrl(downloadUrl);

            return fileUploadResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public byte[] getFile(String fileName) {
        var file = new File(Path.of(this.uploadPath,fileName).toUri());

        try(var fileStream = new FileInputStream(file)){
            return fileStream.readAllBytes();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * @param fileName
     */
    @Override
    public boolean deleteFile(String fileName) {
        var file = new File(Path.of(this.uploadPath,fileName).toUri());

         return file.delete();

    }
}
