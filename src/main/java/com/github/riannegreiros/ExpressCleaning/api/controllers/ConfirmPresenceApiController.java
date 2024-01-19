package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ConfirmPresenceApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.RentACleanerPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/daily/{id}/confirm-presence")
public class ConfirmPresenceApiController {
    @Autowired
    private ConfirmPresenceApiService service;

    @PatchMapping
    @RentACleanerPermissions.isClientFromDaily
    public MessageResponse confirmPresence(@PathVariable Long id) {
        return service.confirmPresence(id);
    }
}
