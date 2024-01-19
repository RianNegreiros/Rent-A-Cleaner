package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.assemblers.UserAssembler;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiMeService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class MeApiController {
    @Autowired
    private ApiMeService service;

    @Autowired
    private UserAssembler assembler;

    @ExpressCleaningPermissions.isHousekeeperOrClient
    @GetMapping
    public UserResponse me() {

        var response = service.getLoggedUser();

        assembler.addLinks(response);

        return response;
    }
}