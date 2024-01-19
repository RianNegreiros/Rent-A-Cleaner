package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.CleanerAddressRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CleanerAddressResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.CleanerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CleanerAddressApiMapper {
    CleanerAddressApiMapper INSTANCE = Mappers.getMapper(CleanerAddressApiMapper.class);

    CleanerAddress toModel(CleanerAddressRequest request);

    CleanerAddressResponse toResponse(CleanerAddress model);
}
