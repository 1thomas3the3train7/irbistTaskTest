package com.example.irbisBlock.exception;

public class GenerateFileException extends RuntimeException {
    public GenerateFileException() {
        super();
    }

    public GenerateFileException(String message) {
        super(message);
    }

    public GenerateFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenerateFileException(Throwable cause) {
        super(cause);
    }
}
