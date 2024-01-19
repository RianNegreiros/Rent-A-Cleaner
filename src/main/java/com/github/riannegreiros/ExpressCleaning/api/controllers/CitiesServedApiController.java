package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.CitiesServedRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CityServedResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.CitiesServedApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
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
    @ExpressCleaningPermissions.isHousekeeper
    public List<CityServedResponse> listCitiesServed() {
        return service.listCitiesServed();
    }

    @PutMapping
    @ExpressCleaningPermissions.isHousekeeper
    public MessageResponse updateCitiesServed(@RequestBody @Valid CitiesServedRequest request) {
        return service.updateCitiesServed(request);
    }
}
