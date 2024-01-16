package com.github.riannegreiros.ExpressCleaning.core.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class HourLaterThanValidator implements ConstraintValidator<HourLaterThan, LocalDateTime> {

    private int startTime;

    @Override
    public void initialize(HourLaterThan constraintAnnotation) {
        startTime = constraintAnnotation.startTime();
        validateParameters();
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        return value.getHour() >= startTime;
    }

    private void validateParameters() {
        if (startTime < 0) {
            throw new IllegalArgumentException("startTime cannot be negative");
        }
    }

}
