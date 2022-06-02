package com.example.firstspringbootapp.validation;

import com.example.firstspringbootapp.annotation.Unique;
import com.example.firstspringbootapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class CustomUniqueValidator implements ConstraintValidator<Unique, String> {

    private final QuestionRepository questionRepository;

    @Override
    public void initialize(Unique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return questionRepository.findQuestionByName(name).isEmpty();
    }
}