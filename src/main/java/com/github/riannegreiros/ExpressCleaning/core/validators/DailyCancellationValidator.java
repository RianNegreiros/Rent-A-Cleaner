package com.github.riannegreiros.ExpressCleaning.core.validators;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@Component
public class DailyCancellationValidator {
    public void validate(Daily daily) {
        validateStatus(daily);
    }

    private void validateStatus(Daily daily) {
        var isNotPaidOrNotConfirmed = !(daily.isPaid() || daily.isConfirmed());
        if (isNotPaidOrNotConfirmed) {
            var message = "Daily rate to be canceled must have the status PAID or CONFIRMED";
            var fieldError = new FieldError(daily.getClass().getName(), "status", daily.getStatus(), false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateAttendanceDate(daily);
    }

    private void validateAttendanceDate(Daily daily) {
        var today = LocalDateTime.now();
        var isAttendanceDateInThePast = daily.getAttendanceDate().isBefore(today);
        if (isAttendanceDateInThePast) {
            var message = "It is no longer possible to cancel the daily.";
            var fieldError = new FieldError(daily.getClass().getName(), "attendanceDate", daily.getAttendanceDate(), false, null, null, message);
            throw new ValidationException(message, fieldError);
        }
    }
}
