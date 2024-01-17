package com.github.riannegreiros.ExpressCleaning.core.validators;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@Component
public class ConfirmPresenceValidator {
    public void validate(Daily daily) {
        validateStatus(daily);
    }

    private void validateStatus(Daily daily) {
        if (!daily.isConfirmed()) {
            var message = "Daily rate not in CONFIRMED status";
            var fieldError = new FieldError(daily.getClass().getName(), "status", daily.getStatus(), false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateAttendanceDate(daily);
    }

    private void validateAttendanceDate(Daily daily) {
        var today = LocalDateTime.now();
        var attendanceDate = daily.getAttendanceDate();

        if (attendanceDate.isAfter(today)) {
            var message = "The per diem service date must be in the past";
            var fieldError = new FieldError(daily.getClass().getName(), "attendanceDate", daily.getAttendanceDate(), false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateHousekeeper(daily);
    }

    private void validateHousekeeper(Daily daily) {
        if (daily.getHousekeeper() == null) {
            var message = "The daily rate does not include a selected housekeeper";
            var fieldError = new FieldError(daily.getClass().getName(), "housekeeper", daily.getHousekeeper(), false, null, null, message);
            throw new ValidationException(message, fieldError);
        }
    }
}
