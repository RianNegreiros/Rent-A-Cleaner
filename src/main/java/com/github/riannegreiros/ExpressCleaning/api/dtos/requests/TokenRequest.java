package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TokenRequest {
    @NotNull
    @NotEmpty
    @Email
    @Size(max = 255)
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String password;

    public TokenRequest() {
    }

    public TokenRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
