package com.studymouse.studymouseserver.controller;

/**
 * Created by jyami on 2020/07/14
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> invalidIdException(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
