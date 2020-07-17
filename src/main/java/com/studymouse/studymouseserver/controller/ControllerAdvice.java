package com.studymouse.studymouseserver.controller;

/**
 * Created by jyami on 2020/07/14
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> invalidIdException(Exception e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> jsonProcessingException(Exception e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
