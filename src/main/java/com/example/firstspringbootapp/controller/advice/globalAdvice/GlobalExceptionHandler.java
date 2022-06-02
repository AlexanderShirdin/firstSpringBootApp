package com.example.firstspringbootapp.controller.advice.globalAdvice;

import com.example.firstspringbootapp.exception.*;
import com.example.firstspringbootapp.payload.response.MessageError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<MessageError> exceptionHandler() {
        MessageError message = new MessageError();
        message.setMessage("Invalid URL parameter id. Please enter a number to URL parameter");
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(AnswerNotFoundException exception) {
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(LevelNotFoundException exception) {
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(ProfileNotFoundException exception) {
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(QuestionNotFoundException exception) {
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(AnswerQuantityMismatchException exception) {
        MessageError messageError = new MessageError();
        messageError.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<MessageError> exceptionHandler(SQLException exception) {
        MessageError messageError = new MessageError();
        messageError.setMessage("Incorrect data");
        return ResponseEntity.badRequest().body(messageError);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> exceptionHandler(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = exception.getBindingResult();
        bindingResult.getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String defaultMessage = error.getDefaultMessage();
                    errors.put(fieldName, defaultMessage);
                });
        return ResponseEntity.badRequest().body(errors);
    }
}