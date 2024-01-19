package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.assemblers.DailyAssembler;
import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.DailyRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiDailyService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily")
public class DailyApiController {
    @Autowired
    private ApiDailyService service;

    @Autowired
    private DailyAssembler assembler;

    @ExpressCleaningPermissions.isClient
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DailyResponse register(@RequestBody @Valid DailyRequest request) {

        return service.register(request);
    }

    @ExpressCleaningPermissions.isHousekeeperOrClient
    @GetMapping
    public List<DailyResponse> listByLoggedUser() {
        var response = service.listByLoggedUser();

        assembler.addLinks(response);

        return response;
    }

    @ExpressCleaningPermissions.isClientOrHousekeeperFromDaily
    @GetMapping("/{id}")
    public DailyResponse findById(@PathVariable Long id) {

        var response = service.findById(id);

        assembler.addLinks(response);

        return response;
    }
}
