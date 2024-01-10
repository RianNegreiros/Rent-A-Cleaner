package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiMeService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class ApiMeController {
    @Autowired
    private ApiMeService service;

    @ExpressCleaningPermissions.isHousekeeperOrClient
    @GetMapping
    public UserResponse me() {

        return service.getLoggedUser();
    }
}