package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.PasswordResetConfirmationRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.PasswordResetRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.PasswordsDoNotMatchException;
import com.github.riannegreiros.ExpressCleaning.core.services.PasswordResetService;
import com.github.riannegreiros.ExpressCleaning.core.services.email.adapters.EmailService;
import com.github.riannegreiros.ExpressCleaning.core.services.email.dtos.EmailParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.HashMap;

@Service
public class ApiPasswordResetService {
    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private EmailService emailService;

    @Value("${frontend.host}")
    private String hostFrontend;

    public MessageResponse requestPasswordReset(PasswordResetRequest request) {
        var passwordReset = passwordResetService.createPasswordReset(request.getEmail());

        if (passwordReset != null) {
            var props = new HashMap<String, Object>();
            props.put("link", hostFrontend + "/reset-password?token=" + passwordReset.getToken());
            var emailParams = new EmailParams.Builder()
                    .destination(request.getEmail())
                    .subject("Password reset request")
                    .template("emails/reset-password")
                    .props(props)
                    .build();
            emailService.sendEmailWithTemplateHtml(emailParams);
        }

        return new MessageResponse("VCheck your e-mail for the password reset link");
    }

    public MessageResponse confirmPasswordReset(PasswordResetConfirmationRequest request) {
        validatePasswordConfirmation(request);
        passwordResetService.resetPassword(request.getToken(), request.getPassword());
        return new MessageResponse("Password changed successfully!");
    }

    private void validatePasswordConfirmation(PasswordResetConfirmationRequest request) {
        var password = request.getPassword();
        var passwordConfirmation = request.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation)) {
            var message = "The two password fields don't match";
            var fieldError = new FieldError(request.getClass().getName(), "passwordConfirmation", request.getPasswordConfirmation(), false, null, null, message);
            throw new PasswordsDoNotMatchException(message, fieldError);
        }
    }
}
