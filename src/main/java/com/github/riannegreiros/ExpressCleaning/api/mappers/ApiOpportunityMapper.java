package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.OpportunityResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.RatingResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.ServiceModel;
import com.github.riannegreiros.ExpressCleaning.core.repositories.RatingRepository;
import com.github.riannegreiros.ExpressCleaning.core.repositories.ServiceRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = ApiOpportunityMapper.class)
public abstract class ApiOpportunityMapper {

    @Autowired
    protected ServiceRepository serviceRepository;

    @Autowired
    protected RatingRepository ratingRepository;

    @Autowired
    protected ApiRatingMapper mapper;

    public static final ApiOpportunityMapper INSTANCE = Mappers.getMapper(ApiOpportunityMapper.class);

    @Mapping(target = "status", source = "status.id")
    @Mapping(target = "serviceName", source = "service.name")
    @Mapping(target = "service", source = "service.id")
    @Mapping(target = "clientRatings", source = "model")
    public abstract OpportunityResponse toResponse(Daily model);

    protected List<RatingResponse> mapClientRatings(Daily model) {
        var client = model.getClient();
        return ratingRepository.getLastRatings(client)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    protected ServiceModel longToService(Long serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(IllegalArgumentException::new);
    }
}