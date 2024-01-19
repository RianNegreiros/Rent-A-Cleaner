package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.DailyCancellationRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.DailyCancellationApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.RentACleanerPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/daily/{id}/cancel")
public class DailyCancellationApiController {
    @Autowired
    private DailyCancellationApiService service;

    @PatchMapping
    @RentACleanerPermissions.isClientOrCleanerFromDaily
    public MessageResponse cancel(
            @PathVariable Long id,
            @RequestBody @Valid DailyCancellationRequest request
    ) {
        return service.cancel(id, request);
    }
}
