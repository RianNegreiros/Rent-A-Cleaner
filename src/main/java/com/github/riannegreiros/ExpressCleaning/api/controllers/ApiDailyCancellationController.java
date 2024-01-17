package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.DailyCancellationRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiDailyCancellationService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/daily/{id}/cancel")
public class ApiDailyCancellationController {
    @Autowired
    private ApiDailyCancellationService service;

    @PatchMapping
    @ExpressCleaningPermissions.isClientOrHousekeeperFromDaily
    public MessageResponse cancel(
            @PathVariable Long id,
            @RequestBody @Valid DailyCancellationRequest request
    ) {
        return service.cancel(id, request);
    }
}
