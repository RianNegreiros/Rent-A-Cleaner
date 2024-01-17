package com.github.riannegreiros.ExpressCleaning.web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PasswordResetForm {
    @NotNull
    @NotEmpty
    @Email
    private String email;

    public PasswordResetForm() {
    }

    public PasswordResetForm(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
