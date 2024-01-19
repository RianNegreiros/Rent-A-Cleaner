package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.CitiesServedRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CityServedResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.CitiesServedApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.RentACleanerPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/cities-served")
public class CitiesServedApiController {
    @Autowired
    private CitiesServedApiService service;

    @GetMapping
    @RentACleanerPermissions.isCleaner
    public List<CityServedResponse> listCitiesServed() {
        return service.listCitiesServed();
    }

    @PutMapping
    @RentACleanerPermissions.isCleaner
    public MessageResponse updateCitiesServed(@RequestBody @Valid CitiesServedRequest request) {
        return service.updateCitiesServed(request);
    }
}
