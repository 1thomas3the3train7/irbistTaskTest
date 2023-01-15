package com.example.irbisBlock.exception;

public class SourceNotFoundException extends RuntimeException{
    public SourceNotFoundException() {
        super();
    }

    public SourceNotFoundException(String message) {
        super(message);
    }

    public SourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
