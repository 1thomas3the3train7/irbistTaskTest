package com.example.irbisBlock.exception;

public class NotValidRequestException extends RuntimeException{
    public NotValidRequestException() {
        super("Not valid request (null)");
    }

    public NotValidRequestException(String message) {
        super(message);
    }

    public NotValidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidRequestException(Throwable cause) {
        super(cause);
    }
}
