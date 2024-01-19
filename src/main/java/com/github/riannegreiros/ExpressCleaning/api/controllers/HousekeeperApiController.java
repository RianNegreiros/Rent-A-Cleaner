package com.github.riannegreiros.ExpressCleaning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.AvailabilityResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.HousekeeperLocalityPagedResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.HousekeeperApiService;

@RestController
@RequestMapping("/api/housekeepers")
public class HousekeeperApiController {

  @Autowired
  private HousekeeperApiService service;

  @GetMapping("/localities")
  public HousekeeperLocalityPagedResponse getHousekeepersByZipCode(
      @RequestParam(required = false) String zipCode) {
    return service.getHousekeeperByZipCode(zipCode);
  }

  @GetMapping("/availability")
  public AvailabilityResponse verifyAvailabilityByZipCode(
      @RequestParam(required = false) String zipCode) {
    return service.verifyAvailabilityByZipCode(zipCode);
  }
}
