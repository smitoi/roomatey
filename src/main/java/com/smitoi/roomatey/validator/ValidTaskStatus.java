package com.smitoi.roomatey.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {ValidTaskStatusValidator.class})
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface ValidTaskStatus {
    public static final String MESSAGE = "The status for this task is not valid!";

    String message() default MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}