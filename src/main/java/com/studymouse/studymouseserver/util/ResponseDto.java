package com.studymouse.studymouseserver.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto<T> {

    public static final ResponseDto<Void> FAIL_DEFAULT_RES
            = ResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, ResponseMessage.INTERNAL_SERVER_ERROR);
    private int status;
    private String message;
    private T response;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private ResponseDto(int status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public static ResponseDto<Void> of(HttpStatus httpStatus, String message) {
        int status = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
        return new ResponseDto<>(status, message, null);
    }

    public static <T> ResponseDto<T> of(HttpStatus httpStatus, String message, T response) {
        int status = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
        return new ResponseDto<>(status, message, response);
    }
}