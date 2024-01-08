package com.github.riannegreiros.ExpressCleaning.web.services;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.ServiceModelNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.ServiceModel;
import com.github.riannegreiros.ExpressCleaning.core.repositories.ServiceRepository;
import com.github.riannegreiros.ExpressCleaning.web.dtos.ServiceForm;
import com.github.riannegreiros.ExpressCleaning.web.mappers.WebServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebServiceService {

  @Autowired
  private ServiceRepository repository;

  @Autowired
  private WebServiceMapper mapper;

  public List<ServiceModel> getAll() {
    return repository.findAll();
  }

  public ServiceModel register(ServiceForm form) {
    var model = mapper.toModel(form);

    return repository.save(model);
  }

  public ServiceModel getById(Long id) {
    var existingService = repository.findById(id);

    if (existingService.isPresent()) {
      return existingService.get();
    }

    var message = String.format("Service with ID %d not found", id);
    throw new ServiceModelNotFoundException(message);
  }

  public ServiceModel edit(ServiceForm form, Long id) {
    var existingService = getById(id);

    var model = mapper.toModel(form);
    model.setId(existingService.getId());

    return repository.save(model);
  }

  public void deleteById(Long id) {
    var existingService = getById(id);

    repository.delete(existingService);
  }
}