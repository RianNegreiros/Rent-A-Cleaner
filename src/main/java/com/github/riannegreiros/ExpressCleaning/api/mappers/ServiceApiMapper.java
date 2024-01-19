package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.ServiceResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.ServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServiceApiMapper {
    ServiceApiMapper INSTANCE = Mappers.getMapper(ServiceApiMapper.class);

    ServiceResponse toResponse(ServiceModel model);
}
