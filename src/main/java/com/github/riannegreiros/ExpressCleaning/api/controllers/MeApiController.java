package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.assemblers.UserAssembler;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.MeApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.RentACleanerPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class MeApiController {
    @Autowired
    private MeApiService service;

    @Autowired
    private UserAssembler assembler;

    @RentACleanerPermissions.isCleanerOrClient
    @GetMapping
    public UserResponse me() {

        var response = service.getLoggedUser();

        assembler.addLinks(response);

        return response;
    }
}