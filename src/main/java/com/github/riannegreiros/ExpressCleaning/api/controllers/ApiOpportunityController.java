package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiOpportunityService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Opportunities")
public class ApiOpportunityController {

    @Autowired
    private ApiOpportunityService service;

    @GetMapping
    @ExpressCleaningPermissions.isHousekeeper
    public List<DailyResponse> searchOpportunities() {
        return service.searchOpportunities();
    }
}
