package com.github.riannegreiros.ExpressCleaning.core.validators;

import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class TransferValidator {
    public void validate(Daily daily) {
        validaStatus(daily);
    }

    private void validaStatus(Daily daily) {
        var validStatus = List.of(DailyStatus.COMPLETED, DailyStatus.REVIEWED);
        if (!validStatus.contains(daily.getStatus())) {
            var message = "Invalid status for transfer";
            var fieldError = new FieldError(daily.getClass().getName(), "status", daily.getStatus(), false, null, null, message);
            throw new ValidationException(message, fieldError);
        }
    }
}
