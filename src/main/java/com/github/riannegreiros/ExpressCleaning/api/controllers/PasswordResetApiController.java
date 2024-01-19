package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.PasswordResetConfirmationRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.PasswordResetRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.PasswordResetApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reset-password")
public class PasswordResetApiController {
    @Autowired
    private PasswordResetApiService service;

    @PostMapping
    public MessageResponse requestPasswordReset(@RequestBody @Valid PasswordResetRequest request) {
        return service.requestPasswordReset(request);
    }

    @PostMapping("/confirm")
    public MessageResponse confirmPasswordReset(@RequestBody @Valid PasswordResetConfirmationRequest request) {
        return service.confirmPasswordReset(request);
    }
}
