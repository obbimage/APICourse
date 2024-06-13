package com.app.course.repository;

import com.app.course.config.AlertQuery;
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

    public static ResponseEntity<RepositoryObject> resultOk(Object object){
        return ResponseEntity.status(HttpStatus.OK).body(
                new RepositoryObject(Status.OK,AlertQuery.QUERY_SUCCESS,object)
        );
    }

    public static ResponseEntity<RepositoryObject> resultFail(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new RepositoryObject(Status.FAILED,AlertQuery.CANT_NOT_FOUND)
        );
    }
    public static ResponseEntity<RepositoryObject> resultFail(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new RepositoryObject(Status.FAILED,message)
        );
    }

    public static ResponseEntity<RepositoryObject> resultError(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new RepositoryObject(Status.ERR,AlertQuery.ERR,message)
        );
    }
}
