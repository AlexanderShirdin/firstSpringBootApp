package com.example.firstspringbootapp.controller.advice.globalAdvice;

import com.example.firstspringbootapp.exception.AnswerNotFoundException;
import com.example.firstspringbootapp.exception.LevelNotFoundException;
import com.example.firstspringbootapp.exception.ProfileNotFoundException;
import com.example.firstspringbootapp.exception.QuestionNotFoundException;
import com.example.firstspringbootapp.payload.response.MessageError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}