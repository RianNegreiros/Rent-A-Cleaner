package com.github.riannegreiros.ExpressCleaning.core.validators;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;

@Component
public class ApplicationValidator {

    @Autowired
    private SecurityUtils securityUtils;

    public void validate(Daily daily) {
        validateApplicantAddress(daily);
    }

    private void validateApplicantAddress(Daily daily) {
        var candidate = securityUtils.getLoggedUser();

        if (candidate.getAddress() == null) {
            var message = "Cleaner must have registered address";
            var fieldError = new FieldError(daily.getClass().getName(), "candidates", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateDuplicityCandidate(daily);
    }

    private void validateDuplicityCandidate(Daily daily) {
        var candidate = securityUtils.getLoggedUser();
        var candidates = daily.getCandidates();

        if (candidates.contains(candidate)) {
            var message = "Cleaner has already applied for this daily";
            var fieldError = new FieldError(daily.getClass().getName(), "candidates", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateQuantityCandidates(daily);
    }

    private void validateQuantityCandidates(Daily daily) {
        var candidates = daily.getCandidates();

        if (candidates.size() >= 3) {
            var message = "Diary already has a maximum number of candidates";
            var fieldError = new FieldError(daily.getClass().getName(), "candidates", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateStatusDaily(daily);
    }

    private void validateStatusDaily(Daily daily) {
        if (!daily.isPaid()) {
            var message = "Daily rate not in PAID status";
            var fieldError = new FieldError(daily.getClass().getName(), "status", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateDailyDate(daily);
    }

    private void validateDailyDate(Daily daily) {
        var today = LocalDateTime.now();
        var attendanceDate = daily.getAttendanceDate();

        if (today.isAfter(attendanceDate)) {
            var message = "Daily rate with date of service in the past";
            var fieldError = new FieldError(daily.getClass().getName(), "attendanceDate", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }

        validateDailyCleaner(daily);
    }

    private void validateDailyCleaner(Daily daily) {
        var cleaner = daily.getCleaner();

        if (cleaner != null) {
            var message = "Daily already has a cleaner";
            var fieldError = new FieldError(daily.getClass().getName(), "cleaner", null, false, null, null, message);
            throw new ValidationException(message, fieldError);
        }
    }
}
