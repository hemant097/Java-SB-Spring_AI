package com.example.project.springai.exception;

public class UnknownAPIException extends RuntimeException {

    public UnknownAPIException() {
        super();
    }

    public UnknownAPIException(String message) {
        super(message);
    }
}
