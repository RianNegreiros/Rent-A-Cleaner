package com.github.riannegreiros.ExpressCleaning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.AvailabilityResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CleanerLocalityPagedResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.CleanerApiService;

@RestController
@RequestMapping("/api/cleaners")
public class CleanerApiController {

  @Autowired
  private CleanerApiService service;

  @GetMapping("/localities")
  public CleanerLocalityPagedResponse getCleanersByZipCode(
      @RequestParam(required = false) String zipCode) {
    return service.getCleanersByZipCode(zipCode);
  }

  @GetMapping("/availability")
  public AvailabilityResponse verifyAvailabilityByZipCode(
      @RequestParam(required = false) String zipCode) {
    return service.verifyAvailabilityByZipCode(zipCode);
  }
}
