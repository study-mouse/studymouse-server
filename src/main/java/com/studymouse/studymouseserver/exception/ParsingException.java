package com.studymouse.studymouseserver.exception;

/**
 * Created by jyami on 2020/07/18
 */
public class ParsingException extends RuntimeException {
    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
