package com.github.riannegreiros.ExpressCleaning.core.validators;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class PaymentValidator {

    public void validate(Daily daily) {
        validateStatus(daily);
    }

    private void validateStatus(Daily daily) {
        if (!daily.isWithoutPayment()) {
            var message = "the daily rate must have the status WITHOUT PAYMENT";
            var fieldError = new FieldError(daily.getClass().getName(), "status", daily.getStatus(), false, null, null, message);

            throw new ValidationException(message, fieldError);
        }
    }
}
