package com.github.riannegreiros.ExpressCleaning.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.AvailabilityResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.HousekeeperLocalityPagedResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.HousekeeperService;

@RestController
@RequestMapping("/api/housekeepers")
public class ApiHousekeeperController {

  @Autowired
  private HousekeeperService service;

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
