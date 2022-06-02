package com.example.firstspringbootapp.exception;

public class AnswerQuantityMismatchException extends RuntimeException {
    public AnswerQuantityMismatchException(String message) {
        super(message);
    }
}
