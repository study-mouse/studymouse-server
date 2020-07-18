package com.studymouse.studymouseserver.exception;

/**
 * Created by jyami on 2020/07/18
 */
public class NotAccessUserException extends RuntimeException{
    public NotAccessUserException(String message) {
        super(message);
    }

    public NotAccessUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
