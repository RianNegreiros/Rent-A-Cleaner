package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.mappers.CleanerApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.AvailabilityResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CleanerLocalityPagedResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.adapters.AddressService;

@Service
public class CleanerApiService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private CleanerApiMapper mapper;

  @Autowired
  private AddressService addressService;

  public CleanerLocalityPagedResponse getCleanersByZipCode(String zipCode) {
    var ibgeCode = getIbgeCodeByZipCode(zipCode);

    var userSort = Sort.sort(User.class);
    var sort = userSort.by(User::getReputation).descending();

    var numPage = 0;
    var pageSize = 6;
    var pageable = PageRequest.of(numPage, pageSize, sort);

    var result = repository.findByCitiesServedIbgeCode(ibgeCode, pageable);
    var cleaners = result.getContent()
        .stream()
        .map(mapper::toCleanerLocalityResponse)
        .toList();

    return new CleanerLocalityPagedResponse(cleaners, pageSize, result.getTotalElements());
  }

  public AvailabilityResponse verifyAvailabilityByZipCode(String zipCode) {
    var ibgeCode = getIbgeCodeByZipCode(zipCode);

    var availability = repository.existsByCitiesServedIbgeCode(ibgeCode);

    return new AvailabilityResponse(availability);
  }

  private String getIbgeCodeByZipCode(String zipCode) {
    return addressService.getAddressByZipCode(zipCode).getIbge();
  }
}
