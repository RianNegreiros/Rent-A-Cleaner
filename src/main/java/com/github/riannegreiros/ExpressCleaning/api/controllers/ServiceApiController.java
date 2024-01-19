package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.ServiceResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceApiController {

    @Autowired
    private ApiServiceService service;

    @GetMapping
    public List<ServiceResponse> getAll() {
        return service.getAll();
    }
}
