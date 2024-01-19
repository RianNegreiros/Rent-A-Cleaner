package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.ServiceResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.ServiceApiMapper;
import com.github.riannegreiros.ExpressCleaning.core.models.ServiceModel;
import com.github.riannegreiros.ExpressCleaning.core.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceApiService {

    @Autowired
    private ServiceRepository repository;

    @Autowired
    private ServiceApiMapper mapper;

    public List<ServiceResponse> getAll() {
        var serviceSort = Sort.sort(ServiceModel.class);
        var order = serviceSort.by(ServiceModel::getPosition).ascending();

        return repository.findAll(order)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
