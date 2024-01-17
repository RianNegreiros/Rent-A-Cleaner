package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiConfirmPresenceService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/daily/{id}/confirm-presence")
public class ApiConfirmPresenceController {
    @Autowired
    private ApiConfirmPresenceService service;

    @PatchMapping
    @ExpressCleaningPermissions.isClientFromDaily
    public MessageResponse confirmPresence(@PathVariable Long id) {
        return service.confirmPresence(id);
    }
}
