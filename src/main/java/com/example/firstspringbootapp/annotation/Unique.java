package com.example.firstspringbootapp.annotation;

import com.example.firstspringbootapp.validation.CustomUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomUniqueValidator.class)
public @interface Unique {
    String message() default "Field is exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
