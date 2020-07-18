package com.studymouse.studymouseserver.controller;

/**
 * Created by jyami on 2020/07/14
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studymouse.studymouseserver.util.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<?>> invalidIdException(Exception e) {
        return ResponseEntity.status(400)
                .body(ResponseDto.of(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ResponseDto<?>> jsonProcessingException(Exception e) {
        return ResponseEntity.status(500)
                .body(ResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}
