package com.app.course.service.file;

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
public class FileServiceIpm implements FileService {
    @Value("${file.upload-path}")
    private String uploadPath;
    //    @Value("${file.download-url}")
    private String uploadPathVideo = "video";
    private String uploadPathImg = "img";
    private final String downloadUrl = "http://localhost:8080/";

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
        var targetPath = this.uploadPath + "/" + randomFileName;

        // save file
        // check if upload path directory exist or not
        try {
            if (!Files.exists(Path.of(uploadPath))) {
                Files.createDirectories(Path.of(uploadPath));
            }
            // save file
            Files.copy(file.getInputStream(), Path.of(targetPath));

            // return file upload response
            FileUploadResponse fileUploadResponse = new FileUploadResponse();
            fileUploadResponse.setFileName(randomFileName);
//            downloadUrl += randomFileName;
            fileUploadResponse.setDownloadUrl(downloadUrl + this.uploadPath + "/" + randomFileName);

            return fileUploadResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileUploadResponse uploadFile(String path, MultipartFile file) {
        String uploadPath = path;
        // Get original filename
        var originalFileName = file.getOriginalFilename();

        // Extract file extension from original file name
        // Egg: image.jpg -> .jpg
        var fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // create random file name
        var randomFileName = UUID.randomUUID() + fileExtension;

        // create target path
        var targetPath = uploadPath + "/" + randomFileName;

        // save file
        // check if upload path directory exist or not
        try {
            if (!Files.exists(Path.of(uploadPath))) {
                Files.createDirectories(Path.of(uploadPath));
            }
            // save file
            Files.copy(file.getInputStream(), Path.of(targetPath));

            // return file upload response
            FileUploadResponse fileUploadResponse = new FileUploadResponse();
            fileUploadResponse.setFileName(randomFileName);
//            downloadUrl += randomFileName;
            fileUploadResponse.setDownloadUrl(downloadUrl + uploadPath + "/" + randomFileName);

            return fileUploadResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileUploadResponse uploadImg(MultipartFile imgFile) {
        var originalFileName = imgFile.getOriginalFilename();
        if(isImageFile(originalFileName)){
            String path = this.uploadPath + "/" + this.uploadPathImg;
            return uploadFile(path,imgFile);
        }
        else
            return null;
    }

    private boolean isImageFile(String fileName) {
        String[] imageExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".svg", ".tiff"};
        for (String extension : imageExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the file extension indicates a video file
    private boolean isVideoFile(String fileName) {
        String[] videoExtensions = {".mp4", ".mov", ".avi", ".mkv", ".wmv", ".flv", ".webm", ".3gp", ".mpg", ".mpeg"};
        for (String extension : videoExtensions) {
            if (fileName.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public FileUploadResponse uploadVideo(MultipartFile file) {
            // Get original filename
            var originalFileName = file.getOriginalFilename();

            // Extract file extension from original file name
            // Egg: image.jpg -> .jpg
            var fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            if (isVideoFile(fileExtension)) {
                String path = this.uploadPath + "/" + this.uploadPathVideo;
                return uploadFile(path, file);
            } else
                return null;
    }

    /**
     * @return
     */
    @Override
    public byte[] getFile(String fileName) {
        var file = new File(Path.of(this.uploadPath, fileName).toUri());

        try (var fileStream = new FileInputStream(file)) {
            return fileStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getFile(String path, String fileName) {
        var file = new File(Path.of(path, fileName).toUri());

        try (var fileStream = new FileInputStream(file)) {
            return fileStream.readAllBytes();
        } catch (IOException e) {
            return null;
//            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getFileImg(String fileName) {
        String pathImg = this.uploadPath+"/" + this.uploadPathImg + "/";
        return getFile(pathImg,fileName);
    }

    @Override
    public byte[] getFileVideo(String fileName) {
        String pathVideo = this.uploadPath +"/" + this.uploadPathVideo + "/";
        return getFile(pathVideo, fileName);
    }

    /**
     * @param fileName
     */
    @Override
    public boolean deleteFile(String fileName) {
        var file = new File(Path.of(this.uploadPath, fileName).toUri());
        return file.delete();
    }

    @Override
    public boolean deleteFileVideo(String fileName) {
        String path = this.uploadPath + "/" + this.uploadPathVideo;
        var file = new File(Path.of(path,fileName).toUri());
        return file.delete();
    }

    @Override
    public boolean deleteFileImg(String fileName) {
        String path = this.uploadPath +"/" + this.uploadPathImg + "/";
        var file = new File(Path.of(path,fileName).toUri());
        return file.delete();
    }

    public String getDownLoadUrl() {
        return downloadUrl;
    }

    // tách tên ảnh từ link down load ảnh
    public String getNameFileFromDownLoadUrl(String downloadUrl) {
        // Lấy URL cần so sánh
        String urlToCompare = getDownLoadUrl();

        // Kiểm tra xem URL cần so sánh có nằm trong URL truyền vào không
        if (downloadUrl.contains(urlToCompare)) {
            // Tìm vị trí của '/' cuối cùng trong URL
            int lastIndex = downloadUrl.lastIndexOf("/");

            // Lấy tên file từ URL, bắt đầu từ vị trí sau '/'
            String fileName = downloadUrl.substring(lastIndex + 1);

            return fileName;
        } else {
            // Trả về chuỗi rỗng hoặc thông báo lỗi nếu cần
            return null;
        }
    }

}
