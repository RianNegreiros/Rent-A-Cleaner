package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.RatingRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiRatingService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/daily/{id}/rating")
public class ApiRatingController {
    @Autowired
    private ApiRatingService service;

    @PatchMapping
    @ExpressCleaningPermissions.isClientOrHousekeeperFromDaily
    public MessageResponse rateDaily(
            @RequestBody @Valid RatingRequest request, @PathVariable Long id
    ) {
        return service.rateDaily(request, id);
    }
}
