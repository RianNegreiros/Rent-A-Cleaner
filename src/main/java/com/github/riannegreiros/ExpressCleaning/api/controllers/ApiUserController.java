package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.UserRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    private ApiUserService service;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponse register(@ModelAttribute @Valid UserRequest request) {

        return service.register(request);
    }
}
