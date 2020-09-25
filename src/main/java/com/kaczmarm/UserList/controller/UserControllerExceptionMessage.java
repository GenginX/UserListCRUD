package com.kaczmarm.UserList.controller;

import com.kaczmarm.UserList.exceptionhandler.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionMessage {

    @ExceptionHandler
    public ResponseEntity<String> getUserExceptionMessage(UserException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
