package com.github.riannegreiros.ExpressCleaning.api.mappers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CityServedResponse;
import com.github.riannegreiros.ExpressCleaning.core.models.CityServed;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApiCityServedMapper {
    ApiCityServedMapper INSTANCE = Mappers.getMapper(ApiCityServedMapper.class);

    CityServedResponse toResponse(CityServed model);
}
