package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.HousekeeperAddressRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.HousekeeperAddressResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.HousekeeperAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApiHousekeeperAddressMapper {
    ApiHousekeeperAddressMapper INSTANCE = Mappers.getMapper(ApiHousekeeperAddressMapper.class);

    HousekeeperAddress toModel(HousekeeperAddressRequest request);

    HousekeeperAddressResponse toResponse(HousekeeperAddress model);
}
