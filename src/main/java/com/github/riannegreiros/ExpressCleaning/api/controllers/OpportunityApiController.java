package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.assemblers.OpportunityAssembler;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.OpportunityApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.RentACleanerPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityApiController {

    @Autowired
    private OpportunityApiService service;

    @Autowired
    private OpportunityAssembler assembler;

    @GetMapping
    @RentACleanerPermissions.isCleaner
    public List<DailyResponse> searchOpportunities() {
        var response = service.searchOpportunities();
        assembler.addLinks(response);
        return response;
    }
}
