package com.github.riannegreiros.ExpressCleaning.core.validators;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import com.github.riannegreiros.ExpressCleaning.core.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@Component
public class RatingValidator {
    @Autowired
    private RatingRepository repository;

    public void validate(Rating model) {
        validateDailyStatus(model);
    }

    private void validateDailyStatus(Rating model) {
        if (!model.getDaily().isCompleted()) {
            var message = "Daily does not have status COMPLETED";
            var fieldError = new FieldError(model.getClass().getName(), "daily", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateDailyAttendanceDate(model);
    }

    private void validateDailyAttendanceDate(Rating model) {
        var today = LocalDateTime.now();
        var dailyAttendanceDate= model.getDaily().getAttendanceDate();

        if (dailyAttendanceDate.isAfter(today)) {
            var message = "Daily is with the date of service in the future";
            var fieldError = new FieldError(model.getClass().getName(), "daily", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateReviewer(model);
    }

    private void validateReviewer(Rating model) {
        var daily = model.getDaily();
        var reviewer = model.getReviewer();

        if (repository.existsByDailyAndReviewer(daily, reviewer)) {
            var message = "User has already rated this daily";
            var fieldError = new FieldError(model.getClass().getName(), "reviewer", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }
    }
}
