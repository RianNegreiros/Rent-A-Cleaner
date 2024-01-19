package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.RefreshRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.TokenRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.TokenResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthApiController {

    @Autowired
    private ApiAuthService service;

    @PostMapping("/token")
    public TokenResponse authenticate(@RequestBody @Valid TokenRequest tokenRequest) {
        return service.authenticate(tokenRequest);
    }

    @PostMapping("/refresh")
    public TokenResponse reAuthenticate(@RequestBody @Valid RefreshRequest refreshRequest) {
        return service.reAuthenticate(refreshRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody @Valid RefreshRequest refreshRequest) {
        service.logout(refreshRequest);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
}
