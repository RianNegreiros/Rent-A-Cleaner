package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class PasswordResetRequest {
    @Email
    @Nonnull
    @NotEmpty
    private String email;

    public PasswordResetRequest() {
    }

    public PasswordResetRequest(@Nonnull String email) {
        this.email = email;
    }

    @Nonnull
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nonnull String email) {
        this.email = email;
    }
}
