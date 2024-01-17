package com.github.riannegreiros.ExpressCleaning.web.services;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.PasswordsDoNotMatchException;
import com.github.riannegreiros.ExpressCleaning.core.services.PasswordResetService;
import com.github.riannegreiros.ExpressCleaning.core.services.email.adapters.EmailService;
import com.github.riannegreiros.ExpressCleaning.core.services.email.dtos.EmailParams;
import com.github.riannegreiros.ExpressCleaning.web.dtos.PasswordResetConfirmationForm;
import com.github.riannegreiros.ExpressCleaning.web.dtos.PasswordResetForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.HashMap;

@Service
public class WebPasswordResetService {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private EmailService emailService;

    @Value("${backend.host}")
    private String hostBackend;

    public void requestPasswordReset(PasswordResetForm form) {
        var passwordReset = passwordResetService.createPasswordReset(form.getEmail());

        if (passwordReset != null) {
            var props = new HashMap<String, Object>();
            props.put("link", hostBackend + "/admin/reset-password/confirmation?token=" + passwordReset.getToken());
            var emailParams = new EmailParams.Builder()
                    .destination(form.getEmail())
                    .subject("Password reset request")
                    .template("emails/reset-password")
                    .props(props)
                    .build();
            emailService.sendEmailWithTemplateHtml(emailParams);
        }
    }

    public void confirmPasswordReset(String token, PasswordResetConfirmationForm form) {
        validatePasswordConfirmation(form);
        passwordResetService.resetPassword(token, form.getPassword());
    }

    private void validatePasswordConfirmation(PasswordResetConfirmationForm form) {
        var password = form.getPassword();
        var passwordConfirmation = form.getPasswordConfirmation();

        if (!password.equals(passwordConfirmation)) {
            var message = "The two password fields don't match";
            var fieldError = new FieldError(form.getClass().getName(), "passwordConfirmation", form.getPasswordConfirmation(), false, null, null, message);
            throw new PasswordsDoNotMatchException(message, fieldError);
        }
    }
}
