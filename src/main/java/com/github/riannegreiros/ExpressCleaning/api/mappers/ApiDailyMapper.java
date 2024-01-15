package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.DailyRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.ServiceModel;
import com.github.riannegreiros.ExpressCleaning.core.repositories.ServiceRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = ApiUserDailyMapper.class)
public abstract class ApiDailyMapper {

    @Autowired
    protected ServiceRepository serviceRepository;

    public static final ApiDailyMapper INSTANCE = Mappers.getMapper(ApiDailyMapper.class);

    public abstract Daily toModel(DailyRequest request);

    @Mapping(target = "status", source = "status.id")
    @Mapping(target = "serviceName", source = "service.name")
    @Mapping(target = "service", source = "service.id")
    public abstract DailyResponse toResponse(Daily model);

    protected ServiceModel longToService(Long serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
