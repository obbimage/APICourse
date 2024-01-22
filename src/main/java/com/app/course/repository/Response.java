package com.app.course.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    /*
    * @AUTHOR: SINH TIEN
    * @SINCE: 8/27/2023 4:09 PM
    * @DESCRIPTION:  result for client
    * @UPDATE:
    *
    * */
    public static ResponseEntity<RepositoryObject> result(HttpStatus httpStatusCode, String status, String message, Object object){
        return ResponseEntity.status(httpStatusCode).body(
                new RepositoryObject(status,message,object)
        );
    }
    public static ResponseEntity<RepositoryObject> result(HttpStatus httpStatusCode, String status, String message){
        return ResponseEntity.status(httpStatusCode).body(
                new RepositoryObject(status,message)
        );
    }
}
