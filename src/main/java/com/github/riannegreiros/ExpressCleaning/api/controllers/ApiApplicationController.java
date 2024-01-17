package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiApplicationService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/daily/{id}/apply")
public class ApiApplicationController {

    @Autowired
    private ApiApplicationService service;

    @PostMapping
    @ExpressCleaningPermissions.isHousekeeper
    public MessageResponse apply(@PathVariable Long id) {
        return service.apply(id);
    }
}
