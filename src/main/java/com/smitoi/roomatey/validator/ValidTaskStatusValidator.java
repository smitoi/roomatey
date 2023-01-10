package com.smitoi.roomatey.validator;


import com.smitoi.roomatey.entity.Task;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ValidTaskStatusValidator implements ConstraintValidator<ValidTaskStatus, String> {
    @Override
    public void initialize(ValidTaskStatus constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String[] statuses = {
                Task.STATUS_PENDING, Task.STATUS_STARTED, Task.STATUS_FINISHED,
        };

        return Arrays.asList(statuses).contains(value);
    }
}